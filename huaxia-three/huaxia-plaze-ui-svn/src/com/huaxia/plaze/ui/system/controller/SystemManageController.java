package com.huaxia.plaze.ui.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.plaze.ui.system.service.DictionaryService;
import com.huaxia.plaze.ui.system.service.UserService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value = "system")
public class SystemManageController {

	@Resource(name = "pageProperty")
	private PageProperty page;

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private UserService userService;

	@ResponseBody
	@RequestMapping("loadDictByKey")
	public String loadDictionaryByKey(@RequestParam String groupCode) throws JsonProcessingException {
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		String jsonString = dictionaryService.queryObjectByGroup(groupCode);
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Iterator<String> it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next();
				if ("dictCode".equals(key)) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("key", jsonObject.get(key));
					jsonMap.put("value", jsonObject.get("dictName"));
					jsonList.add(jsonMap);
				}
			}
		}

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("json", jsonList);
		response.put("selected", false);
		response.put("key", "1");
		response.put("ignore", false);
		return JSONObject.fromObject(response).toString();
	}

	@ResponseBody
	@RequestMapping("loadDictByKeyAuth")
	public String loadDictionaryByKeyAuth(@RequestParam String groupCode, HttpServletRequest request)
			throws IOException {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);
		User user = userService.queryById(loginUser.getUserId());
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		String jsonString = dictionaryService.queryObjectByGroup(groupCode);
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Iterator<String> it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next();
				if ("dictCode".equals(key)) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("key", jsonObject.get(key));
					jsonMap.put("value", jsonObject.get("dictName"));
					jsonList.add(jsonMap);
				}
			}
		}

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("json", jsonList);
		response.put("auth", user.getPbocAuthQuery());
		return JSONObject.fromObject(response).toString();
	}
	@ResponseBody
	@RequestMapping("queryDictName")
	public String queryDictName(String groupCode,String value) throws JsonProcessingException {
		String jsonString = dictionaryService.queryObjectByGroup(groupCode);
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.get("dictCode").equals(value)){
				return jsonObject.get("dictName").toString();
			}
			
		}

		
		return null;
	}
}
