package com.huaxia.plaze.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.Cache;
import com.huaxia.util.CacheUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class DictionarySupport {
	
	protected final static String NAMESPACE = Cache.NAMESPACE;
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getCache(String group) {
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		String body = CacheUtil.get(NAMESPACE, "DICTIONARY", group);
		JSONArray jsonArray = JSONArray.fromObject(body);
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if (jsonObject.isNullObject()) {
					continue;
				}
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
			return jsonList;
		}
		return new ArrayList<Map<String, Object>>();
	}

	public static Object getCache(String group, String key) {
		List<Map<String, Object>> list = getCache(group);
		for (Map<String, Object> object : list) {
			if (String.valueOf(object.get("key")).equals(key)) {
				return object.get("value");
			}
		}
		return null;
	}

}
