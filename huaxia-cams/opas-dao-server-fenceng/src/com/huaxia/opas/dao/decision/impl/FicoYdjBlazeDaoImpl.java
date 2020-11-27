package com.huaxia.opas.dao.decision.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.FicoYdjBlazeDao;
import com.huaxia.opas.domain.input.FicoYdjBlaze;

public class FicoYdjBlazeDaoImpl extends AbstractDAO  implements FicoYdjBlazeDao  {
	private static final long serialVersionUID = 2757582418545513971L;
	
	private static final String NAMESPACES = "FicoYdjBlaze.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(FicoYdjBlaze record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(FicoYdjBlaze record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public FicoYdjBlaze selectByPrimaryKey(String appId) {
		return (FicoYdjBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", appId));
	}
	@Override
	public FicoYdjBlaze selectByAppId(String appId) {
		return (FicoYdjBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId));
	}
	@Override
	public FicoYdjBlaze selectSystemDecisionByAppId(String appId) {
		return (FicoYdjBlaze) (getSqlMap().queryForObject(NAMESPACES + "selectSystemDecisionByAppId", appId));
	}
	@Override
	public int updateByPrimaryKeySelective(FicoYdjBlaze record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FicoYdjBlaze record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
   
}