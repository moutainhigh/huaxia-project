package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasThdMsgDao;

public class OpasThdMsgDaoImpl extends AbstractDAO implements OpasThdMsgDao,Serializable {

	@Override
	public List<Map<String, Object>> queryDateAndCountLimit(String limitModuleType) {
		return getSqlMap().queryForList("OpasThdMsg.queryDateAndCountLimit", limitModuleType);
	}

	@Override
	public int queryTxyysCurrentCount(Map<String, Object> map) {
		return getSqlMap().queryForObject("OpasThdMsg.queryTxyysCurrentCount", map);
	}

	@Override
	public int queryPhoneCurrentCount(Map<String, Object> map) {
		return getSqlMap().queryForObject("OpasThdMsg.queryPhoneCurrentCount", map);
	}

}
