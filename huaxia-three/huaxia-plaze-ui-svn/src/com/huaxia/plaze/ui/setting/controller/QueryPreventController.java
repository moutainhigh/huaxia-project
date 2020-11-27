package com.huaxia.plaze.ui.setting.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.setting.domain.ConfigSetting;
import com.huaxia.plaze.ui.setting.service.ConfigSettingService;
import com.huaxia.plaze.ui.system.common.SystemSettings;
import com.huaxia.util.CacheUtil;

import net.sf.json.JSONObject;

/**
 * 查询阻断配置
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "setting")
public class QueryPreventController {

	@Resource
	private ConfigSettingService configSettingService;

	@RequestMapping("prevent/index")
	public String queryIndex(ModelMap modelMap) {
		// 人行单条实时查询阻断编号
		modelMap.put("queryConfigIdOfA", SystemSettings.PBOC_SINGLE_QUERY_MAX_COUNT.getCode());
		modelMap.put("queryConfigNameOfA", SystemSettings.PBOC_SINGLE_QUERY_MAX_COUNT.getDesc());

		// 人行单条实时查找阻断编号
		modelMap.put("searchConfigIdOfA", SystemSettings.PBOC_SINGLE_SEARCH_MAX_COUNT.getCode());
		modelMap.put("searchConfigNameOfA", SystemSettings.PBOC_SINGLE_SEARCH_MAX_COUNT.getDesc());

		// 相同证件号码N小时查询阻断编号
		modelMap.put("queryConfigIdOfB", SystemSettings.SAME_CERTNO_QUERY_MAX_COUNT.getCode());
		modelMap.put("queryConfigNameOfB", SystemSettings.SAME_CERTNO_SEARCH_MAX_COUNT.getDesc());

		// 相同证件号码N小时查找阻断编号
		modelMap.put("searchConfigIdOfB", SystemSettings.SAME_CERTNO_SEARCH_MAX_COUNT.getCode());
		modelMap.put("searchConfigNameOfB", SystemSettings.SAME_CERTNO_SEARCH_MAX_COUNT.getDesc());

		return "setting/query_prevent_setting";
	}

	/** 人行单条实时查询及查找阻断 */
	@ResponseBody
	@RequestMapping("prevent/query/a")
	public String querySettingOfA(String id) {
		ConfigSetting setting = configSettingService.querySettingById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		if (setting != null) {
			response.put("result", true);
			response.put("record", setting);
		}
		return JSONObject.fromObject(response).toString();
	}

	/** 相同证件号码N小时查询及查找阻断 */
	@ResponseBody
	@RequestMapping("prevent/query/b")
	public String querySettingOfB(String id) {
		ConfigSetting setting = configSettingService.querySettingById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		if (setting != null) {
			response.put("result", true);

			// 分钟转化小时
			BigDecimal hours = new BigDecimal(setting.getIntervalTime()).divide(new BigDecimal("60"));
			setting.setIntervalTime(Integer.parseInt(hours.toString()));

			response.put("record", setting);
		}
		return JSONObject.fromObject(response).toString();
	}

	/**
	 * 人行阻断配置保存
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@ResponseBody
	@RequestMapping("prevent/save")
	public String saveSetting(HttpServletRequest request, @RequestBody String jsonString)
			throws JsonGenerationException, JsonMappingException, IOException {
		JSONObject object = JSONObject.fromObject(jsonString);
		if (object.isNullObject() || object.isEmpty()) {
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("result", false);
			return new ObjectMapper().writeValueAsString(response);
		}

		// 阻断编号
		String id = String.valueOf(object.get("id"));

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", id);
		args.put("configName", String.valueOf(object.get("name")));
		args.put("crtUser", CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getRequestedSessionId()));
		args.put("maxCount", Integer.parseInt(String.valueOf(object.get("maxCount"))));

		String intervalTime = String.valueOf(object.get("intervalTime"));
		// 相同证件号码N小时查询及查找阻断配置
		// 小时转换成分钟
		if ("000103".equals(id) || "000104".equals(id)) {
			// 小时转化分钟
			BigDecimal minutes = new BigDecimal(intervalTime).multiply(new BigDecimal("60"));
			args.put("intervalTime", Integer.parseInt(minutes.toString()));
		}
		// 其他阻断情况
		else {
			args.put("intervalTime", Integer.parseInt(intervalTime));
		}

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		int result = configSettingService.mergeSetting(args);
		if (result > 0) {
			response.put("result", true);
		}
		return new ObjectMapper().writeValueAsString(response);
	}

}
