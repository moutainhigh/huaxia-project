package com.huaxia.opas.dao.decision.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.BizApprovalOpeexchisDao;
import com.huaxia.opas.domain.input.BizApprovalOpeexchis;

public class BizApprovalOpeexchisDaoImpl extends AbstractDAO implements BizApprovalOpeexchisDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "BizApprovalOpeexchis.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(BizApprovalOpeexchis record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(BizApprovalOpeexchis record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public BizApprovalOpeexchis selectByPrimaryKey(String autoId) {
		return (BizApprovalOpeexchis) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public BizApprovalOpeexchis selectByUserCode(String userCode) {
		return (BizApprovalOpeexchis) (getSqlMap().queryForObject(NAMESPACES + "selectByUserCode", userCode));
	}

	@Override
	public int updateByPrimaryKeySelective(BizApprovalOpeexchis record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BizApprovalOpeexchis record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

}