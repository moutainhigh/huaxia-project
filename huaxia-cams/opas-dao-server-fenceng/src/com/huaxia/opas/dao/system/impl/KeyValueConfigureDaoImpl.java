package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.KeyValueConfigureDao;
import com.huaxia.opas.domain.system.KeyValueConfigure;

public class KeyValueConfigureDaoImpl extends AbstractDAO implements KeyValueConfigureDao {

	private static final long serialVersionUID = -3264784776149209726L;
	
	private static final String NAMESPACES = "KeyValueConfigure.";

	@Override
	public List<KeyValueConfigure> queryKeyValueConfigureList(KeyValueConfigure keyValueConfigure, int curNum, int pageNum) {
		List<KeyValueConfigure> list = new ArrayList<KeyValueConfigure>();
		list = getSqlMap().queryForList(NAMESPACES + "queryKeyValueConfigureList", keyValueConfigure, curNum, pageNum);
		return list;
	}

	@Override
	public int queryKeyValueConfigureCount(KeyValueConfigure keyValueConfigure) {
		return getSqlMap().queryForObject(NAMESPACES + "queryKeyValueConfigureCount", keyValueConfigure);
	}

	@Override
	public int insertKeyValueConfigure(KeyValueConfigure keyValueConfigure) {
		return getSqlMap().insert(NAMESPACES + "insertKeyValueConfigure", keyValueConfigure);
	}

	@Override
	public int updateKeyValueConfigure(KeyValueConfigure keyValueConfigure) {
		return getSqlMap().update(NAMESPACES + "updateKeyValueConfigure", keyValueConfigure);
	}

	@Override
	public KeyValueConfigure queryKeyValueConfigureById(String autoId) {
		return (KeyValueConfigure) (getSqlMap().queryForObject(NAMESPACES + "queryKeyValueConfigureById", autoId));
	}

	@Override
	public int queryKeyValueConfigureEqualCount(KeyValueConfigure keyValueConfigure) {
		return getSqlMap().queryForObject(NAMESPACES + "queryKeyValueConfigureEqualCount", keyValueConfigure);
	}

	@Override
	public String queryAutoIdEqualByKeyOrValue(KeyValueConfigure keyValueConfigure) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAutoIdEqualByKeyOrValue", keyValueConfigure);
	}

	@Override
	public String getValueContentByKeyName(String keyName) {
		return getSqlMap().queryForObject(NAMESPACES + "getValueContentByKeyName", keyName);
	}
	
	@Override
	public List<KeyValueConfigure> getValueContentListByKeyName(String[] keyName) {
		return getSqlMap().queryForList(NAMESPACES + "getValueContentListByKeyName", keyName);
	}
}
