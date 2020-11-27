package com.huaxia.plaze.ui.setting.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.setting.domain.ConfigSetting;
import com.huaxia.plaze.ui.setting.service.ConfigSettingService;
import com.huaxia.plaze.ui.system.common.SystemSettings;
import com.huaxia.util.CacheUtil;

import net.sf.json.JSONObject;

/**
 * 系统登录配置
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping(value = "setting")
public class SystemLoginController {

	@Resource
	private ConfigSettingService configSettingService;

	@RequestMapping("login/index")
	public String index(ModelMap modelMap) {
		// 系统登录限制编号
		modelMap.put("queryConfigId", SystemSettings.LOGIN_SYSTEM_MAX_COUNT.getCode());
		modelMap.put("queryConfigName", SystemSettings.LOGIN_SYSTEM_MAX_COUNT.getDesc());
		return "setting/system_login_setting";
	}

	@ResponseBody
	@RequestMapping("login/query")
	public String querySetting(String id) {
		ConfigSetting setting = configSettingService.querySettingById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		if (setting != null) {
			// 分钟转化小时
			BigDecimal hours = new BigDecimal(setting.getIntervalTime()).divide(new BigDecimal("60"));
			setting.setIntervalTime(Integer.parseInt(hours.toString()));

			response.put("result", true);
			response.put("record", setting);
		}
		return JSONObject.fromObject(response).toString();
	}

	@ResponseBody
	@RequestMapping("login/save")
	public String saveSetting(HttpServletRequest request, @RequestBody String jsonString)
			throws JsonGenerationException, JsonMappingException, IOException {
		JSONObject object = JSONObject.fromObject(jsonString);
		if (object.isNullObject() || object.isEmpty()) {
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("result", false);
			return new ObjectMapper().writeValueAsString(response);
		}

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", String.valueOf(object.get("id")));
		args.put("configName", String.valueOf(object.get("name")));
		args.put("crtUser", CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getRequestedSessionId()));
		args.put("maxCount", Integer.parseInt(String.valueOf(object.get("maxCount"))));

		// 小时转化分钟
		String intervalTime = String.valueOf(object.get("intervalTime"));
		BigDecimal minutes = new BigDecimal(intervalTime).multiply(new BigDecimal("60"));
		args.put("intervalTime", Integer.parseInt(minutes.toString()));

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		int result = configSettingService.mergeSetting(args);
		if (result > 0) {
			response.put("result", true);
		}
		return new ObjectMapper().writeValueAsString(response);
	}

}
