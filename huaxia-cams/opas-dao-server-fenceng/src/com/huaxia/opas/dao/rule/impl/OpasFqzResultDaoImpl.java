package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasFqzResultDao;

public class OpasFqzResultDaoImpl extends AbstractDAO implements OpasFqzResultDao ,Serializable{

	@Override
	public List<Map<String,String>> selectFqzResultByAppId(String appId) {
		return getSqlMap().queryForList("OpasFqzMsg.selectFqzResultByAppId", appId);
	}

	@Override
	public List<String> selectFqzIdStatusByAppId(String appId) {
		return getSqlMap().queryForList("OpasFqzMsg.selectFqzResultByAppId", appId);
	}
	
	@Override
	public List<String> selectFqzDecisionLvByAppId(String appId) {
		return getSqlMap().queryForList("OpasFqzMsg.selectFqzDecisionLvByAppId", appId);
	}

	@Override
	public List<String> selectCustRiskMsg(String c1Idnbr) {
		return getSqlMap().queryForList("OpasFqzMsg.selectCustRiskMsg", c1Idnbr);
	}

	@Override
	public List<String> selectInNetTimeTypeByAppId(String appId) {
		return getSqlMap().queryForList("OpasFqzMsg.selectInNetTimeTypeByAppId", appId);
	}

	@Override
	public List<String> selectFqzWsScoreByAppId(String appId) {
		return getSqlMap().queryForList("OpasFqzMsg.selectFqzWsScoreByAppId", appId);
	}

	@Override
	public List<String> selectFqzFwsScoreByAppId(String appId) {
		return getSqlMap().queryForList("OpasFqzMsg.selectFqzFwsScoreByAppId", appId);
	}

}
