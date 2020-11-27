package com.huaxia.plaze.ui.pboc.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.FileHelper;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.common.PbocMapping;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.pboc.service.FileStorageService;
import com.huaxia.plaze.ui.pboc.service.QueryMonitorService;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;
import com.huaxia.plaze.ui.pboc.support.QueryLogSupport;
import com.huaxia.plaze.ui.setting.service.PbocQueryService;
import com.huaxia.plaze.ui.system.controller.AuthorizationController;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 人行征信查询（单条）
 * 
 * @author liya'nan
 * @author zhiguo.li, 20200702, 增加人行查询日志打印。增加原因：排查人行最大查询数量重置为0问题。
 * @see com.huaxia.plaze.interceptor.PbocQueryLimitIntercepter#preHandle(HttpServletRequest, HttpServletResponse, Object)
 *
 */
@Controller
@RequestMapping("pboc/single")
public class SingleQueryController {

	private final static Logger logger = LoggerFactory.getLogger(SingleQueryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;
	
	@Resource(name = "pbocFileHelper")
	private FileHelper fileHelper;

	@Resource
	private PbocQueryService pbocQueryService;

	@Resource
	private SingleQueryService singleQueryService;

	@Resource
	private QueryLogSupport queryLogSupport;

	@Resource
	private AuthorizationController authorizationController;

	@Resource
	private UserService userService;

	@Resource
	private QueryMonitorService queryMonitorService;

	@Resource
	private FileStorageService fileStorageService;

	// 附件上传路径
	public static final String enclosurePath = AppConfig.getInstance().getValue("PBOC_CREDIT_SINGLE_PATH");

	/** 单条实时查询（主页） */
	@RequestMapping("index")
	public String singleIndex() {
		return "pboc/pboc_single_index";
	}

	/** 单条实时查询（提交复核） */
	@RequestMapping("submit")
	@ResponseBody
	public String singleSubmitCheck(HttpServletRequest request, SingleQueryReview queryReview,
			@RequestParam(value = "enclosure") MultipartFile[] files)
			throws JsonParseException, JsonMappingException, IOException {
		//映射字典表中的人行查询原因:贷前审批1 映射为03，贷后2映射为01，特约商户查询3映射为19 
		queryReview.setQueryType(PbocMapping.ResultbyQueryType(queryReview.getQueryType()));
		// 查询接口设置
		String resultInterface = queryMonitorService.queryInterfaceSetting("0");
		logger.info("查询数量接口设置信息：查询条件 -> {} | 查询结果 -> {}", new Object[] { "0", resultInterface });
		if (resultInterface != null) {
			return resultInterface;
		}
		String queryExclude = (String) request.getAttribute("queryExclude");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);

		// 人行查询阻断验证
		String message = null;
		try {
			message = queryMonitorService.checkLimit(queryReview, loginUser, queryExclude);
			logger.info("人行查询阻断验证结果信息: {}", new Object[] { message });
			if (message != null) {
				if (message.indexOf("下线") != -1) {
					logger.warn("本次人行查询操作已被系统阻断，当前登录账户[{}]自动退出", new Object[] { account });
					authorizationController.logout(account);
				}
				return message;
			}
		} catch (Exception e1) {
			logger.error("人行征信查询阻断异常[{}]", e1.getMessage(), e1);
		}
		
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		List<FileStorageInfo> infoList = new ArrayList<>();
		try {
			// 生成批次号
			String batchNo = BatchNoGenerator.generateNextNumber();

			FileStorageInfo info = null;
			for (MultipartFile file : files) {
				File target = new File(fileHelper.getDateStorePath(true, batchNo + "_" + file.getOriginalFilename()));
				File fileParent = target.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}

				// 附件信息
				info = new FileStorageInfo();
				info.setSourceId(batchNo);
				info.setSourceName(file.getOriginalFilename());
				info.setSourcePath(fileHelper.getDateStorePath(false, batchNo + "_" + file.getOriginalFilename()));
				info.setSourceType(
						file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
				info.setCrtUser(loginUser.getAccount());
				infoList.add(info);

				// 上传文件
				file.transferTo(target);
			}
			
			queryReview.setCrtUser(loginUser.getAccount());
			// 待复核状态
			queryReview.setReviewStatus("0");
			// 单条实时
			queryReview.setQueryFrom("0");
			queryReview.setSourceId(batchNo);
			int result = singleQueryService.saveObject(infoList, queryReview);
			if (result <= 0) {
				return "提交失败";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "请求异常";
		}
		if ("例外".equals(mes)) {
			queryLogSupport.queryAfter(queryReview.getTrnId(), "0", "1", "1");
		} else {
			queryLogSupport.queryAfter(queryReview.getTrnId(), "0", "1", "0");
		}
		return "提交成功";
	}

	/** 单条查询复核（列表） */
	@RequestMapping("review/pagelist")
	public String singleReviewPageList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);

		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
       
		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page =new PageProperty();
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		// 设置复核状态为待复核
		page.addParameter("reviewStatus", "0");
		page.addParameter("queryFrom", "0");
		page.addParameter("team", loginUser.getTeam());

		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[查询复核] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = singleQueryService.queryListCountByPage(page);
		List<SingleQueryReview> list = singleQueryService.queryListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_single_review_list";
	}

	/** 提交查询复核 */
	@RequestMapping("submit/review")
	@ResponseBody
	public String singleSubmitQuery(HttpServletRequest request, String id)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		Map<String, Object> args = new HashMap<String, Object>();
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		int status = singleQueryService.checkUser(id);
		if (status > 0) {
			return "操作员用户状态异常 请核实！";
		}
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		args.put("updUser", loginUser.getAccount());
		args.put("id", id);
		args.put("reviewStatus", "1");
		int result = singleQueryService.updateSubmitAgree(args);
		if (result > 0) {
			if ("例外".equals(mes)) {
				queryLogSupport.queryAfter(id, "0", "2", "1");
			} else {
				queryLogSupport.queryAfter(id, "0", "2", "0");
			}
			return "提交成功";
		}
		return "提交失败";
	}

	/** 单条查询退回 */
	@RequestMapping("reject")
	@ResponseBody
	public String singleReject(HttpServletRequest request, String id, String refuseReason)
			throws JsonParseException, JsonMappingException, IOException {
		String queryExclude = (String) request.getAttribute("queryExclude");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("updUser", loginUser.getAccount());
		args.put("id", id);
		args.put("refuseReason", refuseReason);
		args.put("reviewStatus", "5");
		int result = singleQueryService.updateSubmitReject(args);
		// 插入查询日志
		if (result > 0) {
			queryLogSupport.queryAfter(id, "0", "3", queryExclude);
			return "提交成功";
		}
		return "提交失败";
	}

	/**
	 * 人行征信查询--查询复核页面
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping("review/query")
	public String singleQueryReview(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page =new PageProperty();
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		// 设置复核状态为待复核
		page.addParameter("reviewStatus", "0");
		page.addParameter("queryFrom", "0");
		page.addParameter("team", loginUser.getTeam());

		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[查询复核] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = singleQueryService.queryListCountByPage(page);
		List<SingleQueryReview> list = singleQueryService.queryListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_single_query_review";
	}

	/** 单条实时查询（提交查询） */
	@RequestMapping("agree/single")
	@ResponseBody
	public String submitAgree(HttpServletRequest request, String id)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		Map<String, Object> args = new HashMap<String, Object>();
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		int status = singleQueryService.checkUser(id);
		if (status > 0) {
			return "操作员用户状态异常 请核实！";
		}
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		args.put("updUser", loginUser.getAccount());
		args.put("id", id);
		args.put("reviewStatus", "1");
		int result = singleQueryService.updateSubmitAgree(args);
		if (result > 0) {
			if ("例外".equals(mes)) {
				queryLogSupport.queryAfter(id, "0", "2", "1");
			} else {
				queryLogSupport.queryAfter(id, "0", "2", "0");

			}
			return "提交成功";
		}
		return "提交失败";
	}

	/**
	 * 批量提交查询
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping("agree/batch")
	@ResponseBody
	public String batchAgree(HttpServletRequest request, String ids)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		Map<String, Object> args = new HashMap<String, Object>();
		String[] idArray = ids.split(",");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		int status = singleQueryService.checkUser(ids);
		if (status > 0) {
			return "操作员用户状态异常 请核实！";
		}
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		args.put("updUser", loginUser.getAccount());
		args.put("idArray", idArray);
		args.put("reviewStatus", "1");
		int result = singleQueryService.updateBatchAgree(args);
		if (result > 0) {
			if ("例外".equals(mes)) {
				queryLogSupport.queryAfter(ids, "0", "4", "0");
			} else {
				queryLogSupport.queryAfter(ids, "0", "4", "1");
			}
			return "提交成功";
		}
		return "提交失败";
	}

	/**
	 * 批量退回
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping("reject/batch")
	@ResponseBody
	public String batchReject(HttpServletRequest request, String ids, String refuseReason)
			throws JsonParseException, JsonMappingException, IOException {
		String queryExclude = (String) request.getAttribute("queryExclude");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		String[] idArray = ids.split(",");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("updUser", loginUser.getAccount());
		args.put("idArray", idArray);
		args.put("refuseReason", refuseReason);
		args.put("reviewStatus", "5");
		int result = singleQueryService.updateBatchReject(args);
		if (result > 0) {
			// 插入查询日志
			queryLogSupport.queryAfter(ids, "0", "5", queryExclude);
			return "提交成功";
		}
		return "提交失败";
	}

	/** 单条实时查询（复核退回） */
	@RequestMapping("reject/single")
	@ResponseBody
	public String submitReject(HttpServletRequest request, String id, String refuseReason)
			throws JsonParseException, JsonMappingException, IOException {
		String queryExclude = (String) request.getAttribute("queryExclude");
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("updUser", loginUser.getAccount());
		args.put("id", id);
		args.put("refuseReason", refuseReason);
		args.put("reviewStatus", "5");
		int result = singleQueryService.updateSubmitReject(args);
		if (result > 0) {
			// 插入查询日志
			queryLogSupport.queryAfter(id, "0", "3", queryExclude);
			return "提交成功";
		}
		return "提交失败";
	}

	/** 人行单条复核退回 */
	@RequestMapping("review/refuse/pagelist")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page =new PageProperty();
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		// 设置复核状态为待复核
		page.addParameter("reviewStatus", "5");
		// 设置查询来源单条
		page.addParameter("queryFrom", "0");
		page.addParameter("team", loginUser.getTeam());

		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[查询复核] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = singleQueryService.queryListCountByPage(page);
		List<SingleQueryReview> list = singleQueryService.queryListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_single_review_refuse_list";
	}

	/** 修改用户 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String modifyRole(HttpServletRequest request,String id, ModelMap modelMap) {
		SingleQueryReview review = singleQueryService.queryObjectById(id);
		if (review != null) {
			modelMap.put("records", review);
		}
		modelMap.put("queryType", review.getQueryType());
		return "pboc/pboc_single_review_modify";
	}

	/** 提交复核查询 */
	@RequestMapping("submit/query")
	@ResponseBody
	public String submitCheckUpdate(HttpServletRequest request, SingleQueryReview queryReview,
			@RequestParam(value = "enclosure") MultipartFile[] files) {
		//映射字典表中的人行查询原因:贷前审批1 映射为03，贷后2映射为01，特约商户查询3映射为19 
		queryReview.setQueryType(PbocMapping.ResultbyQueryType(queryReview.getQueryType()));
		String queryExclude = (String) request.getAttribute("queryExclude");
		List<FileStorageInfo> infoList = new ArrayList<>();
		User loginUser = null;
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);
			String mes = singleQueryService.checkAuth(loginUser.getUserId());
			if (mes != null && !"例外".equals(mes)) {
				return mes;
			}
			// 生成批次号
			String batchNo = BatchNoGenerator.generateNextNumber();

			FileStorageInfo info = null;
			for (MultipartFile file : files) {
				File target = new File(fileHelper.getDateStorePath(true, batchNo + "_" + file.getOriginalFilename()));
				File fileParent = target.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}

				// 附件信息
				info = new FileStorageInfo();
				info.setSourceId(batchNo);
				info.setSourceName(file.getOriginalFilename());
				info.setSourcePath(fileHelper.getDateStorePath(false, batchNo + "_" + file.getOriginalFilename()));
				info.setSourceType(
						file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
				info.setCrtUser(loginUser.getAccount());
				infoList.add(info);

				// 上传文件
				file.transferTo(target);
			}
			// 设置当前登录用户ID
			queryReview.setUpdUser(loginUser.getAccount());
			// 设置为待复核状态
			queryReview.setReviewStatus("0");
			// 设置存储路径
			queryReview.setSourceId(batchNo);
			int result = singleQueryService.saveObject(infoList, queryReview);
			// 保存
			if (result <= 0) {
				return "提交失败";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			return "请求异常";
		}
		queryLogSupport.queryAfter(queryReview.getTrnId(), "0", "1", queryExclude);
		return "提交成功";
	}
}
