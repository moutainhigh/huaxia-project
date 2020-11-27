package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasApproveReasonDao;
import com.huaxia.opas.domain.rule.OpasApproveReasoncode;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.rule.OpasBizApprovalHis;

public class OpasApproveReasonDaoImpl extends AbstractDAO implements OpasApproveReasonDao ,Serializable{
	private static final String NAMESPACE = "OpasApproveReasoncode.";

	@Override
	public List<OpasApproveReasoncode> selectByObapproval(OpasBizApproval oba) {
		return getSqlMap().queryForList(NAMESPACE+"selectByObapproval", oba);
	}

	@Override
	public List<OpasApproveReasoncode> selectByObapprovalHis(OpasBizApprovalHis obah) {
		return getSqlMap().queryForList(NAMESPACE+"selectByObapprovalHis", obah);
	}

}
