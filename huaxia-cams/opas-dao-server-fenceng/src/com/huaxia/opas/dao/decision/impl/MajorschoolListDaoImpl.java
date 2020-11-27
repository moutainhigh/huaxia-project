package com.huaxia.opas.dao.decision.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.MajorschoolListDao;
import com.huaxia.opas.domain.sysparm.MajorschoolList;

public class MajorschoolListDaoImpl extends AbstractDAO implements MajorschoolListDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "MajorschoolList.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(MajorschoolList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(MajorschoolList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public MajorschoolList selectByPrimaryKey(String autoId) {
		return (MajorschoolList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(MajorschoolList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(MajorschoolList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

}