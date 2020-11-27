package com.huaxia.plaze.ui.id5.controller;

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
import com.huaxia.plaze.ui.id5.service.EducationService;
import com.huaxia.plaze.ui.setting.domain.QueryConfiguration;
import com.huaxia.plaze.ui.setting.service.QueryConfigurateService;
import com.huaxia.util.CacheUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 学历查询任务配置
 * 
 * @author zjw
 *
 */
@Controller
@RequestMapping(value = "id5/edu/configurate")
public class EducationConfigurateController {

	@Resource
	private QueryConfigurateService queryConfiguateService;

	@Resource
	private EducationService educationService;

	// 显示页面
	@RequestMapping("query/index")
	public String index(ModelMap modelMap) throws JsonProcessingException {
		return "id5/education_query_configure";
	}

	/** 显示学历的手动和渠道查询数量 */
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
		List<Map<String, Integer>> queryCountList = educationService.queryCountByParams(args);
		for (Map<String, Integer> queryCount : queryCountList) {
			response.put("TASK" + queryCount.get("TASK_TYPE"), queryCount);
		}

		return JSONObject.fromObject(response).toString();
	}

	/**
	 * 保存学历的手动和渠道的配置
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@ResponseBody
	@RequestMapping("save")
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
		response.put("message", "学历查询设置失败!");
		int result = queryConfiguateService.mergeSetting(args);
		if (result > 0) {
			response.put("result", true);
			response.put("message", "学历查询设置成功!");
		}
		return new ObjectMapper().writeValueAsString(response);
	}

	/** 显示学历的手动和渠道配置的数量和时间 */
	@ResponseBody
	@RequestMapping("showConfig")
	public String queryConfigure() {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("sourceCode", "000300");
		Map<String, Object> response = new HashMap<String, Object>();
		List<QueryConfiguration> queryCountList = queryConfiguateService.querySettingByParams(args);
		for (QueryConfiguration queryCount : queryCountList) {
			response.put("TASK" + queryCount.getSourceCode() + "" + queryCount.getChannelCode(), queryCount);
		}
		return JSONObject.fromObject(response).toString();
	}

}
