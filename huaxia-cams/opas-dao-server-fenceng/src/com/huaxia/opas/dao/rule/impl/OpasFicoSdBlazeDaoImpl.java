package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasFicoSdBlazeDao;
import com.huaxia.opas.domain.input.FicoSdBlaze;

public class OpasFicoSdBlazeDaoImpl extends AbstractDAO  implements OpasFicoSdBlazeDao ,Serializable  {
	private static final long serialVersionUID = 2757582418545513971L;
	
	private static final String NAMESPACES = "FicoSdBlaze.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(FicoSdBlaze record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(FicoSdBlaze record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public FicoSdBlaze selectByPrimaryKey(String autoId) {
		return (FicoSdBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public FicoSdBlaze selectByAppIdSea(String appId) {
		return (FicoSdBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectByAppIdSea", appId));
	}
	public FicoSdBlaze selectByAppId(String appId) {
		return (FicoSdBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId));
	}
	@Override
	public FicoSdBlaze selectSystemDecisionByAppId(String appId) {
		return (FicoSdBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectSystemDecisionByAppId", appId));
	}
	@Override
	public int updateByPrimaryKeySelective(FicoSdBlaze record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FicoSdBlaze record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public String selectCreateTime(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreateTime", appId);
	}

	@Override
	public List<Map<String,Object>> selectFicoMsgByAppId(String appId) {
		return getSqlMap().queryForList("OpasFicoMsg.selectFicoMsgByAppId", appId);
	}
}