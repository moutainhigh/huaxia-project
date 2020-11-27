package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasTyMsgDao;

public class OpasTyMsgDaoImpl extends AbstractDAO implements OpasTyMsgDao ,Serializable {
	
	@Override
	public int queryTyScore(String appId) {
		if(getSqlMap().queryForObject("OpasTyMsg.queryTyScore", appId) == null){
			return -1;
		}else{
			int tyScore = Integer.valueOf((String)getSqlMap().queryForObject("OpasTyMsg.queryTyScore", appId));
			return tyScore;
		}
	}
	
	public List<Map<String,String>> queryTyScoreCodeFound(String appId) {
		return getSqlMap().queryForList("OpasTyMsg.queryTyScoreCodeFound", appId);
	}

	@Override
	public List<Map<String,String>> queryTyRiskLv(String appId) {
		return getSqlMap().queryForList("OpasTyMsg.queryTyRiskLv", appId);
	}
	
}
