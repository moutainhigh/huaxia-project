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
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.FileHelper;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.common.PbocMapping;
import com.huaxia.plaze.exception.ApplicationException;
import com.huaxia.plaze.ui.pboc.domain.BatchQueryReview;
import com.huaxia.plaze.ui.pboc.domain.FileStorageInfo;
import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.pboc.service.BatchQueryService;
import com.huaxia.plaze.ui.pboc.service.SingleQueryService;
import com.huaxia.plaze.ui.pboc.support.QueryLogSupport;
import com.huaxia.plaze.ui.setting.service.PbocQueryService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.BatchNoGenerator;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 人行征信查询（批量）
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping("pboc/batch")
public class BatchQueryController {

	private final static Logger logger = LoggerFactory.getLogger(BatchQueryController.class);

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private BatchQueryService batchQueryService;

	@Resource(name = "pbocFileHelper")
	private FileHelper fileHelper;

	@Resource
	private PbocQueryService pbocQueryService;

	@Resource
	private QueryLogSupport queryLogSupport;

	@Resource
	private SingleQueryService singleQueryService;

	@Resource
	private UserService userService;

	/** 人行批量实时查询 */
	@RequestMapping("index")
	public String batchIndex() {
		return "pboc/pboc_batch_index";
	}

	/** 人行批量提交复核 */
	@RequestMapping("submit/review")
	@ResponseBody
	public String batchSubmitReview(HttpServletRequest request, SingleQueryReview queryReview,
			@RequestParam("files") MultipartFile[] files, @RequestParam("enclosure") MultipartFile[] enclosure) {
		//映射字典表中的人行查询原因:贷前审批1 映射为03，贷后2映射为01，特约商户查询3映射为19 
		queryReview.setQueryType(PbocMapping.ResultbyQueryType(queryReview.getQueryType()));
		List<FileStorageInfo> infoList = new ArrayList<>();
		User loginUser = null;
		// 生成批次号码
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);
			String mes = singleQueryService.checkAuth(loginUser.getUserId());
			if (mes != null && !"例外".equals(mes)) {
				return mes;
			}
			FileStorageInfo info = null;
			for (MultipartFile file : enclosure) {
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
			queryReview.setCrtUser(loginUser.getAccount());
			// 设置为待复核状态
			queryReview.setReviewStatus("0");
			// 设置查询来源批量实时
			queryReview.setQueryFrom("1");
			try {
				int result = singleQueryService.saveObjectBatch(infoList, queryReview, files, batchNo);
				if (result <= 0) {
					return "提交失败";
				}
			} catch (ApplicationException e) {
				return e.getMessage();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "提交失败";
		}
		return "提交成功!批次号[" + batchNo + "]";
	}
	
	/** 人行批量实时提交（查询） */
	@RequestMapping("submit/query")
	@ResponseBody
	public String batchSubmitQuery(HttpServletRequest request, String batchId)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> args = new HashMap<String, Object>();
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		args.put("updUser", loginUser.getAccount());
		args.put("batchId", batchId);
		args.put("reviewStatus", "1");
		int result = batchQueryService.updateSubmitAgreeBatch(args);
		if (result > 0) {
			return "提交成功";
		}
		return "提交失败";
	}

	/** 人行批量查询复核 */
	@RequestMapping("review/pagelist")
	public String batchQueryReviewPageList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		page =new PageProperty();
		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}
		page.clearDataMap();
		// 设置复核状态为待复核
		page.addParameter("reviewStatus", "0");
		page.addParameter("team", loginUser.getTeam());
		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[查询复核] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = batchQueryService.queryBatchIdListCountByPage(page);
		List<BatchQueryReview> list = batchQueryService.queryBatchIdListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_batch_review_list";
	}
	
	/** 批量复核退回提交 */
	@RequestMapping("review/submit")
	@ResponseBody
	public String batchSubmitOfReview(HttpServletRequest request, SingleQueryReview queryReview,
			@RequestParam("files") MultipartFile[] files, @RequestParam("enclosure") MultipartFile[] enclosure,
			String oldBatchNo) {
		//映射字典表中的人行查询原因:贷前审批1 映射为03，贷后2映射为01，特约商户查询3映射为19 
		queryReview.setQueryType(PbocMapping.ResultbyQueryType(queryReview.getQueryType()));
		List<FileStorageInfo> infoList = new ArrayList<>();
		User loginUser = null;
		// 生成批次号
		String batchNo = BatchNoGenerator.generateNextNumber();
		try {
			String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
			String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
			loginUser = JacksonUtil.jsonToObject(body, User.class);
			String mes = singleQueryService.checkAuth(loginUser.getUserId());
			if (mes != null && !"例外".equals(mes)) {
				return mes;
			}
			FileStorageInfo info = null;
			for (MultipartFile file : enclosure) {
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
			queryReview.setCrtUser(loginUser.getAccount());
			// 设置为待复核状态
			queryReview.setReviewStatus("0");
			// 设置查询来源批量实时
			queryReview.setQueryFrom("1");
			int result = batchQueryService.batchUpdate(infoList, queryReview, files, batchNo, oldBatchNo.trim());
			if (result <= 0) {
				return "提交失败";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "提交失败";
		}
		return "提交成功!批次号[" + batchNo + "]";
	}
	
	/** 人行批量复核退回 */
	@RequestMapping("review/refuse/pagelist")
	public String indexReviewRefuse(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");	
		page=new PageProperty();
		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		// 业务参数
		page.clearDataMap();
		// 查询复核状态为已退回的数据
		page.addParameter("reviewStatus", "5");
		page.addParameter("team", loginUser.getTeam());
		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[查询复核] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}
		int totalCount = batchQueryService.queryBatchIdListCountByPage(page);
		List<BatchQueryReview> list = batchQueryService.queryBatchIdRefuseListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_batch_review_refuse_list";
	}

	/** 批量实时查询单条退回 */
	@RequestMapping("reject/single")
	@ResponseBody
	public String rejectSingle(HttpServletRequest request, String batchId, String refuseReason)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		String mes = singleQueryService.checkAuth(loginUser.getUserId());
		if (mes != null && !"例外".equals(mes)) {
			return mes;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("updUser", loginUser.getAccount());
		args.put("batchId", batchId);
		args.put("refuseReason", refuseReason);
		// 设为复核退回状态
		args.put("reviewStatus", "5");
		int result = batchQueryService.updateSubmitRejectBatch(args);
		if (result > 0) {
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
	@RequestMapping("agree")
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
			return "操作员用户状态异常,请核实!";
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

	/** 批量实时查询批量退回 */
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

	/** 人行批次查询 */
	@RequestMapping("batch/pagelist")
	public String indexBatchResult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		page =new PageProperty();
		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);
		page.addParameter("team", loginUser.getTeam());

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}

		int totalCount = batchQueryService.queryBatchListCountByPage(page);
		List<BatchQueryReview> list = batchQueryService.queryBatchListByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_batch_batch_list";
	}

	/** 人行征信查询--查询文件明细 */
	@RequestMapping(value = "filedetail")
	public String getFileDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		// 分页参数
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		page =new PageProperty();
		int iPageNo = page.getPageNo();
		if (StringUtils.isNotBlank(pageNo)) {
			iPageNo = Integer.parseInt(pageNo);
		}
		int iPageSize = page.getPageSize();
		if (StringUtils.isNotBlank(pageSize)) {
			iPageSize = Integer.parseInt(pageSize);
		}

		// 分页设置
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isDebugEnabled()) {
			logger.debug("[查询复核] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}
		// 业务参数
		page.clearDataMap();
		String fileId = request.getParameter("fileId");
		if (StringUtils.isNotBlank(fileId)) {
			page.addParameter("fileId", fileId);
		}
		if (page.getDataMap().size() > 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("[菜单管理] 查询参数[{}]", new Object[] { page.getDataMap() });
			}
		}

		int totalCount = batchQueryService.queryFileDetailCount(page);
		List<SingleQueryReview> list = batchQueryService.queryFileDetailListByPage(page);

		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", list);
		return "pboc/pboc_batch_filedetail";
	}

	/** 人行征信查询--查询文件明细 */
	@RequestMapping("report/view")
	public String getQueryResult(String trnId, ModelMap modelMap,HttpServletRequest request) {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		try {
			User loginUser = JacksonUtil.jsonToObject(body, User.class);
			modelMap.put("staffName", loginUser.getStaffName());
		} catch (IOException e) {
			logger.error("JSON转换对象异常:{}", new Object[] { e.getMessage() }, e);
		}
		
		return "pboc/pboc_report_view";
	}
	/** 修改用户 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String modify(String batchId, String queryType, ModelMap modelMap) {

		modelMap.put("batchId", batchId);
		modelMap.put("queryType", queryType);
		return "pboc/pboc_batch_review_modify";
	}
}
