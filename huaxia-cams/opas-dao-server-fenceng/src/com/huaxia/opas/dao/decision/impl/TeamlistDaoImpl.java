package com.huaxia.opas.dao.decision.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.TeamlistDao;
import com.huaxia.opas.domain.decision.Teamlist;

public class TeamlistDaoImpl extends AbstractDAO implements TeamlistDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "Teamlist.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(Teamlist record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(Teamlist record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public Teamlist selectByPrimaryKey(String autoId) {
		return (Teamlist) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(Teamlist record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Teamlist record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

}