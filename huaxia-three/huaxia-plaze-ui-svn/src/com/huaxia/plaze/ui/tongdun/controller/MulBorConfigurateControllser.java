package com.huaxia.plaze.ui.tongdun.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.service.QueryConfigurateService;
import com.huaxia.plaze.ui.tongdun.service.MulBorConfigurateService;
import com.huaxia.util.CacheUtil;

import net.sf.json.JSONObject;


/** 多头借贷查询（配置）*/
@Controller
@RequestMapping(value = "tongdun/mulbor/configurate")
public class MulBorConfigurateControllser {

	@Resource
	private QueryConfigurateService queryConfigurateService;

	@Resource
	private MulBorConfigurateService mulBorConfigurateService;

	@RequestMapping("query/index")
	public String index(ModelMap modelMap) throws JsonProcessingException {
		return "tongdun/mulbor_query_setting";
	}

	/** 多头借贷查询配置 */
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
		List<Map<String, Integer>> queryCountList = mulBorConfigurateService.queryCountByParams(args);
		for (Map<String, Integer> queryCount : queryCountList) {
			response.put("TASK" + queryCount.get("TASK_TYPE"), queryCount);
		}
		
		return JSONObject.fromObject(response).toString();
	}

	/**
	 * 多头借贷查询配置保存
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
		response.put("message", "多头借贷查询设置失败!");
		int result = queryConfigurateService.mergeSetting(args);
		if (result > 0) {
			response.put("result", true);
			response.put("message", "多头借贷查询设置成功!");
		}
		return new ObjectMapper().writeValueAsString(response);
	}

	/** 多头借贷查询配置 */
	@ResponseBody
	@RequestMapping("query/configure")
	public String queryConfigure() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sourceCode", "001500");
		Map<String, Object> response = new HashMap<String, Object>();
		List<QueryConfiguration> queryCountList = queryConfigurateService.querySettingByParams(args);
		for (QueryConfiguration queryCount : queryCountList) {
			response.put("TASK" + queryCount.getSourceCode() + "" + queryCount.getChannelCode(), queryCount);
		}
		return JSONObject.fromObject(response).toString();
	}

}
