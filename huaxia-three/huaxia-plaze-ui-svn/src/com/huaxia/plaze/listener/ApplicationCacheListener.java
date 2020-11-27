package com.huaxia.plaze.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.system.domain.Dictionary;
import com.huaxia.plaze.ui.system.service.DictionaryService;
import com.huaxia.plaze.ui.system.service.ResourceService;
import com.huaxia.plaze.ui.system.service.RoleResourceService;
import com.huaxia.plaze.ui.system.service.UserRoleService;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 应用缓存监听器
 * 
 * @author zhiguo.li
 *
 */
public class ApplicationCacheListener implements ApplicationListener<ContextRefreshedEvent> {

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private RoleResourceService roleResourceService;
	
	@Resource
	private ResourceService resourceService;

	@Resource
	private UserRoleService userRoleService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if ("Root WebApplicationContext".equals(event.getApplicationContext().getDisplayName())) {
			// 删除应用缓存信息
			CacheUtil.delBatch(Cache.NAMESPACE);
			
			// 加载业务字典
			Map<String, List<Dictionary>> cache = new HashMap<String, List<Dictionary>>();
			List<Dictionary> dictionaryList = dictionaryService.queryList();
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
		}
	}

}
