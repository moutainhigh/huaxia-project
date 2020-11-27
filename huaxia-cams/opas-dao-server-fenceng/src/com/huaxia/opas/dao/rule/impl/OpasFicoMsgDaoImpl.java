package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasFicoMsgDao;

public class OpasFicoMsgDaoImpl extends AbstractDAO implements OpasFicoMsgDao ,Serializable {
	
	
	@Override
	public List<String> queryFicoQueryMsg(String appId) {
		return getSqlMap().queryForList("OpasFicoMsg.queryFicoQueryMsg", appId);
	}

	@Override
	public List<Map<String, Object>> queryDateAndCountLimit() {
		return getSqlMap().queryForList("OpasFicoMsg.queryDateAndCountLimit");
	}

	@Override
	public int queryFicoCount() {
		return getSqlMap().queryForObject("OpasFicoMsg.queryFicoCount");
	}

	@Override
	public List<String> queryFicoMsg() {
		return getSqlMap().queryForList("OpasFicoMsg.queryFicoMsg");
	}
	
	@Override
	public List<Map<String, String>> queryRuleContent() {
		return getSqlMap().queryForList("OpasFicoMsg.queryRuleContent");
	}
	
	@Override
	public List<String> queryPbocBadness(String appId) {
		return getSqlMap().queryForList("OpasFicoMsg.queryPbocBadness", appId);
	}
}
