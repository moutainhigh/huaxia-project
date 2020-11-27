package com.huaxia.plaze.ui.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.system.domain.Dictionary;
import com.huaxia.plaze.ui.system.mapper.DictionaryMapper;
import com.huaxia.plaze.ui.system.service.DictionaryService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

import net.sf.json.JSONObject;

@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
	private DictionaryMapper<Dictionary> dictionaryMapper;

	@Override
	public List<Dictionary> queryList() {
		return dictionaryMapper.selectList();
	}

	public String queryObjectByGroup(String group) {
		String jsonString = CacheUtil.get(Cache.NAMESPACE, "DICTIONARY", group);
		if (StringUtils.isBlank(jsonString)) {
			Map<String, List<Dictionary>> cache = new HashMap<String, List<Dictionary>>();
			List<Dictionary> dictionaryList = dictionaryMapper.selectList();
			for (Dictionary dictionary : dictionaryList) {
				String key = dictionary.getGroupCode();
				List<Dictionary> keyList = new ArrayList<Dictionary>();
				if (cache.containsKey(key)) {
					keyList = cache.get(key);
				}
				keyList.add(dictionary);
				cache.put(key, keyList);

				// 如果缓存中存在该分组键值则进行更新, 否则进行缓存
				if (CacheUtil.has(key)) {
					CacheUtil.del(key);
					try {
						CacheUtil.set(Cache.NAMESPACE + ":DICTIONARY:" + key, JacksonUtil.objectToJson(keyList));
					} catch (JsonProcessingException e) {
					}
				} else {
					try {
						CacheUtil.set(Cache.NAMESPACE + ":DICTIONARY:" + key, JacksonUtil.objectToJson(keyList));
					} catch (JsonProcessingException e) {
					}
				}
			}
			JSONObject jsonObject = JSONObject.fromObject(cache);
			if (jsonObject.containsKey(group)) {
				jsonString = jsonObject.getString(group);
			} else {
				jsonString = "[]";
			}
		}
		return jsonString;
	}

	public String queryName(Map<String, String> args) {
		return dictionaryMapper.selectName(args);
	}

}
