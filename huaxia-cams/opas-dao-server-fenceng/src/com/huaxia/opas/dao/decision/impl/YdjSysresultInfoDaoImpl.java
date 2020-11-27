package com.huaxia.opas.dao.decision.impl;

import java.io.Serializable;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.YdjSysresultInfoDao;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;

public class YdjSysresultInfoDaoImpl extends AbstractDAO implements YdjSysresultInfoDao ,Serializable {
	
	private static final long serialVersionUID = 2757581418545513971L;

	private static final String NAMESPACES = "YdjSysresultInfo.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(YdjSysresultInfo record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(YdjSysresultInfo record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public YdjSysresultInfo selectByPrimaryKey(String autoId) {
		return (YdjSysresultInfo) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(YdjSysresultInfo record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(YdjSysresultInfo record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
	@Override
	public YdjSysresultInfo selectSysResultfoByAppId(String appId) {
		return (YdjSysresultInfo) getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId);
	}

	@Override
	public YdjSysresultInfo selectCreditScore(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectCreditScore", appId);
	}

	@Override
	public Float selectCreditScoreYdj(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditScoreYdj", appId);
	}
}