package com.huaxia.plaze.ui.unicom.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.ExecutorSupport;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.service.QueryConfigurateService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.unicom.caller.AddressCallWorker;
import com.huaxia.plaze.ui.unicom.domain.AddressData;
import com.huaxia.plaze.ui.unicom.domain.AddressSingle;
import com.huaxia.plaze.ui.unicom.service.AddressSingleService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONObject;

/**
 * 联通地址类信息 单条实时查询
 * 
 * @author User
 *
 */
@Controller
@RequestMapping("unicom/address/single")
public class AddressController {

	public static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Resource
	private AddressSingleService addressSingleService;

	@Resource
	private QueryConfigurateService queryConfiguateService;

	@Resource(name = "pageProperty")
	private PageProperty page;

	/**
	 * 单条实时查询页面(展示)
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex() {
		return "unicom/address_single_index";
	}

	/**
	 * 单条实时查询（提交查询）
	 * 
	 * 返回值 2:24小时数据有效性 1：成功 0：失败 -1：查询未在时间段内 -2：查询数量已达到限定值
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 */
	@RequestMapping("submit")
	@ResponseBody
	public Object submit(HttpServletRequest request, AddressSingle addressSingle)
			throws JsonParseException, JsonMappingException, IOException {
		int result;

		// 24小时有效性校验
		// Map<String,Object> params = new HashMap<String,Object>();
		// params.put("mobile", addressSingle.getMobile());
		// params.put("apiKey", addressSingle.getApiKey());
		// result = addressSingleService.queryCountByMobile(params);
		// if(result>0){
		// return 2;
		// }
		// 从redis中提取当前登录人信息
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);

		String trnId = GuidUtil.getGuid();
		addressSingle.setTrnId(trnId);
		addressSingle.setCrtUser(account);
		addressSingle.setQueryStatus("0");

		try {
			result = addressSingleService.save(addressSingle);
			if (result == 1) {
				// 调用联机发起实时查询
				Map<String, String> args = new HashMap<String, String>();
				args.put("TRN_ID", trnId);
				args.put("MOBILE", addressSingle.getMobile());
				args.put("API_KEY", addressSingle.getApiKey());
				args.put("ADDRESS", addressSingle.getAddress());
				FutureTask<Boolean> futureTask = new FutureTask<Boolean>(
						new AddressCallWorker(addressSingleService, args));
				ExecutorSupport.EXECUTOR.submit(futureTask);
				if (futureTask.get()) {
					result = 1;
				} else {
					result = 0;
				}
			}

		} catch (Exception e) {
			result = 0;
			logger.error("[联通地址类信息] 单条实时查询异常[{}]", new Object[] { e.getMessage() }, e);
		}
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("result", result);
		resultData.put("trnId", trnId);
		return new ObjectMapper().writeValueAsString(resultData);
	}

	/**
	 * 展示单条实时查询结果
	 * 
	 * @param trnId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("view/detail")
	public String showResult(String trnId, ModelMap modelMap) {
		AddressData info = addressSingleService.querySingleResultByTrnid(trnId);
		if (StringUtils.isNotBlank(info.getCheckResult())) {
			if ("360015".equals(info.getApiKey())) {
				info.setApiKey("工作地址");
			} else if ("360016".equals(info.getApiKey())) {
				info.setApiKey("居住地址");
			}
		}
		modelMap.put("records", JSONObject.fromObject(info));
		return "unicom/address_detail_show";
	}

	/**
	 * 展示历史查询页面
	 * 
	 * @return
	 */
	@RequestMapping("history/index")
	public String showHistoryIndex() {
		return "unicom/address_history_list";
	}

	/**
	 * 返回查询历史数据
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("history/pagelist")
	public String queryListByPage(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String mobile = request.getParameter("mobile");
		// 分页擦书
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
		page.setPageNo(iPageNo);
		page.setPageSize(iPageSize);

		if (logger.isInfoEnabled()) {
			logger.info("[历史数据查询] 当前页号[{}]，每页大小[{}]", new Object[] { iPageNo, iPageSize });
		}
		page.clearDataMap();
		// 手机号
		page.addParameter("mobile", mobile);
		if (logger.isInfoEnabled()) {
			logger.info("[历史数据查询] 查询参数[{}]", new Object[] { page.getDataMap() });
		}
		int totalCount = addressSingleService.queryHistoryListCountByPage(mobile);
		List<AddressData> historyList = addressSingleService.queryHistoryDataByPage(page);
		page.setTotalCount(totalCount);
		modelMap.put("page", page);
		modelMap.put("records", historyList);
		return "unicom/address_history_list";
	}

	/**
	 * 展示数量限制页面
	 * 
	 * @return
	 */
	@RequestMapping("setting/index")
	public String showSetting() {
		return "unicom/address_setting";
	}

	/**
	 * 查询当前已查询数量条数
	 * 
	 * @param startTime
	 *            当前年xxxx年1月1号
	 * @param endTime
	 *            当前年xxxx年12月31号
	 * @return
	 */
	@RequestMapping("setting/query/count")
	@ResponseBody
	public String queryCount(String startTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		Map<String, Object> response = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(startTime)) {
			args.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			args.put("endTime", endTime);
		}
		List<Map<String, Integer>> queryCountList = addressSingleService.queryCountByTime(args);
		for (Map<String, Integer> queryCount : queryCountList) {
			response.put("TASK" + queryCount.get("TASK_TYPE"), queryCount);
		}
		return JSONObject.fromObject(response).toString();
	}

	/**
	 * 展示当前的数量限制的相关参数
	 * 
	 * @return
	 */
	@RequestMapping("setting/query/configure")
	@ResponseBody
	public String queryCountConfig() {
		Map<String, Object> args = new HashMap<String, Object>();
		Map<String, Object> response = new HashMap<String, Object>();
		args.put("sourceCode", "001102");
		List<QueryConfiguration> queryCountList = queryConfiguateService.querySettingByParams(args);
		for (QueryConfiguration queryCount : queryCountList) {
			response.put("TASK" + queryCount.getSourceCode() + queryCount.getChannelCode(), queryCount);
		}
		args.clear();
		args.put("sourceCode", "001103");
		List<QueryConfiguration> querys = queryConfiguateService.querySettingByParams(args);
		for (QueryConfiguration query : querys) {
			response.put("TASK" + query.getSourceCode() + query.getChannelCode(), query);
		}
		return JSONObject.fromObject(response).toString();
	}

	@ResponseBody
	@RequestMapping("setting/query/save")
	public String saveQuerySetting(HttpServletRequest request, @RequestBody String jsonString)
			throws JsonGenerationException, JsonMappingException, IOException {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sourceCode", jsonObject.get("sourceCode"));
		args.put("channelCode", jsonObject.get("channelCode"));
		args.put("maxCount", jsonObject.get("maxCount"));
		args.put("startTime", jsonObject.get("startTime"));
		args.put("endTime", jsonObject.get("endTime"));
		args.put("createUser", CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId()));
		args.put("updateUser", CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId()));

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("message", "地址类信息查询数量设置失败!");
		int result = queryConfiguateService.mergeSetting(args);
		if (result > 0) {
			response.put("result", true);
			response.put("message", "地址类信息查询数量设置成功!");
		}
		return new ObjectMapper().writeValueAsString(response);
	}

}
