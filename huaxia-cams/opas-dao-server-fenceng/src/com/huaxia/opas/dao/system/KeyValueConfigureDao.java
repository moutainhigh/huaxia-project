package com.huaxia.opas.dao.system;

import java.util.List;

import com.huaxia.opas.domain.system.KeyValueConfigure;

public interface KeyValueConfigureDao {

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
	 * 根据多个键名称 查询对应的 键值对列表
	 * @param keyNames
	 * @return
	 */
	public List<KeyValueConfigure> getValueContentListByKeyName(String[] keyNameList);
}
