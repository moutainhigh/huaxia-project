package com.huaxia.plaze.ui.setting.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.service.PbocQueryService;
import com.huaxia.plaze.ui.setting.service.QueryConfigurateService;
import com.huaxia.util.CacheUtil;

import net.sf.json.JSONObject;

/**
 * 人行查询任务配置
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "setting/pboc")
public class PbocQueryController {

	@Resource
	private PbocQueryService pbocQueryService;

	@Resource
	private QueryConfigurateService confQueryCountService;
	
	
	@RequestMapping("query/index")
	public String index(ModelMap modelMap) throws JsonProcessingException {
		return "setting/pboc_query_setting";
	}
	
	/** 修改用户 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String modify(String batchId, String queryType, ModelMap modelMap) {
		modelMap.put("batchId", batchId);
		modelMap.put("queryType", queryType);
		return "pboc/pboc_batch_review_modify";
	}

	/** 人行征信查询配置 */
	@ResponseBody
	@RequestMapping("query/count")
	public String queryCount(String startTime, String endTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(startTime)) {
			args.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			args.put("endTime", endTime);
		}

		Map<String, Object> response = new HashMap<String, Object>();
		List<Map<String, Integer>> queryCountList = pbocQueryService.queryCountByParams(args);
		for (Map<String, Integer> queryCount : queryCountList) {
			response.put("TASK" + queryCount.get("TASK_TYPE"), queryCount);
		}

		return JSONObject.fromObject(response).toString();
	}

	/** 人行征信查询配置 */
	@ResponseBody
	@RequestMapping("query/configure")
	public String queryConfigure() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sourceCode", "000100");
		Map<String, Object> response = new HashMap<String, Object>();
		List<QueryConfiguration> queryCountList = confQueryCountService.querySettingByParams(args);
		for (QueryConfiguration queryCount : queryCountList) {
			response.put("TASK" + queryCount.getSourceCode()+""+queryCount.getChannelCode(), queryCount);
		}
		return JSONObject.fromObject(response).toString();
	}

	/**
	 * 人行征信查询配置保存
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@ResponseBody
	@RequestMapping("query/configure/save")
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
		response.put("message", "人行征信查询设置失败!");
		int result = confQueryCountService.mergeSetting(args);
		if (result > 0) {
			response.put("result", true);
			response.put("message", "人行征信查询设置成功!");
		}
		return new ObjectMapper().writeValueAsString(response);
	}

	/** 人行查询授权配置查询 */
	@RequestMapping("query/authorize/index")
	public String queryAuthorizeIndex(ModelMap modelMap) throws JsonProcessingException {
		Map<String, String> queryAuthorize = pbocQueryService.queryAuthorizeSettingByParams(null);
		modelMap.put("record", queryAuthorize);
		return "setting/pboc_query_authorize_setting";
	}

	/**
	 * 人行查询授权配置保存（查询用户+查询密码）
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@ResponseBody
	@RequestMapping("query/authorize/configure/save")
	public String saveQueryAuthorizeSetting(HttpServletRequest request, @RequestBody String jsonString)
			throws JsonGenerationException, JsonMappingException, IOException {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("queryUserBefore", jsonObject.get("queryUserBefore"));
		args.put("queryUser", jsonObject.get("queryUser"));
		args.put("queryPass", jsonObject.get("queryPass"));
		args.put("querySource", "01"); // 01-人行征信查询
		args.put("createUser", CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId()));
		args.put("updateUser", CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		response.put("message", "人行查询授权配置失败!");
		int result = pbocQueryService.mergeQueryAuthorizeSetting(args);
		if (result > 0) {
			response.put("result", true);
			response.put("message", "人行查询授权配置成功!");
		}
		return new ObjectMapper().writeValueAsString(response);
	}
}
