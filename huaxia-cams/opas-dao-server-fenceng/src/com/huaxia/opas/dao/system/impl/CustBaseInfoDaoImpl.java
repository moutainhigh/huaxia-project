package com.huaxia.opas.dao.system.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.CustBaseInfoDao;
import com.huaxia.opas.domain.system.CustBaseInfo;

public class CustBaseInfoDaoImpl extends AbstractDAO implements CustBaseInfoDao {

	private static final long serialVersionUID = 1278216697563527223L;

	private static final String NAMESPACES = "CustBaseInfo.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(CustBaseInfo record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(CustBaseInfo record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public CustBaseInfo selectByPrimaryKey(String autoId) {
		return (CustBaseInfo) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(CustBaseInfo record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(CustBaseInfo record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

}
