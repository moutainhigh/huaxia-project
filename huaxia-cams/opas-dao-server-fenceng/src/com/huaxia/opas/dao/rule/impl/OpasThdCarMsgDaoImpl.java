package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasThdCarMsgDao;

public class OpasThdCarMsgDaoImpl extends AbstractDAO implements OpasThdCarMsgDao ,Serializable{
	@Override
	public List<Map<String, Object>> queryDateAndCountLimit() {
		return getSqlMap().queryForList("OpasThdCarMsg.queryDateAndCountLimit");
	}

	@Override
	public int queryVehicleCount() {
		return getSqlMap().queryForObject("OpasThdCarMsg.queryVehicleCount");
	}

	@Override
	public List<String> queryVehicleMsg() {
		return getSqlMap().queryForList("OpasThdCarMsg.queryVehicleMsg");
	}
	
	@Override
	public List<Map<String, String>> queryRuleContent() {
		return getSqlMap().queryForList("OpasThdCarMsg.queryRuleContent");
	}
	
	@Override
	public int queryTyCount() {
		return getSqlMap().queryForObject("OpasThdCarMsg.queryTyCount");
	}
	
	@Override
	public List<Map<String, Object>> queryTyDateAndCountLimit() {
		return getSqlMap().queryForList("OpasThdCarMsg.queryTyDateAndCountLimit");
	}
	
	@Override
	public List<Map<String, Object>> queryCompanyDateAndCountLimit() {
		return getSqlMap().queryForList("OpasThdCarMsg.queryCompanyDateAndCountLimit");
	}

	@Override
	public int queryCompanyCount() {
		return getSqlMap().queryForObject("OpasThdCarMsg.queryCompanyCount");
	}

	@Override
	public List<String> queryCompanyMsg() {
		return getSqlMap().queryForList("OpasThdCarMsg.queryCompanyMsg");
	}
}
