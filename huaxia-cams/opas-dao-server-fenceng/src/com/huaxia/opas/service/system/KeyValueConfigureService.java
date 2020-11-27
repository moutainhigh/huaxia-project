package com.huaxia.opas.service.system;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.system.KeyValueConfigure;

public interface KeyValueConfigureService {
	
	public List<KeyValueConfigure> queryKeyValueConfigureList(KeyValueConfigure keyValueConfigure, int curNum, int pageNum);
	
	public int queryKeyValueConfigureCount(KeyValueConfigure keyValueConfigure);
	
	public int insertKeyValueConfigure(KeyValueConfigure keyValueConfigure);
	
	public int updateKeyValueConfigure(KeyValueConfigure keyValueConfigure);
	
	public KeyValueConfigure queryKeyValueConfigureById(String autoId);
	
	public int queryKeyValueConfigureEqualCount(KeyValueConfigure keyValueConfigure);
	
	public String queryAutoIdEqualByKeyOrValue(KeyValueConfigure keyValueConfigure);
	
	/**
	 * 根据  键名称 得到对的 值内容
	 * @param keyName
	 * @return
	 */
	public String getValueContentByKeyName(String keyName);
	
	/**
	 * 根据多个键名称 查询对应的 键值对Map集合
	 * @param keyNames 如：
	 * 						Map<String, String> map= keyValueConfigureService.getKVMapByKeyNameArray(new String[]{"f1", "e11"});
	 * 						String valueContent_f1 = map.get("f1");
	 * 						String valueContent_e11 = map.get("e11");
	 * @return
	 */
	public Map<String, String> getKVMapByKeyNameArray(String[] keyName);
}
