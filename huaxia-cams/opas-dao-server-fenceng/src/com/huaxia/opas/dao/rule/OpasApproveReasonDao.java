package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.OpasApproveReasoncode;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.rule.OpasBizApprovalHis;

public interface OpasApproveReasonDao {
	public List<OpasApproveReasoncode> selectByObapproval(OpasBizApproval oba);
	public List<OpasApproveReasoncode> selectByObapprovalHis(OpasBizApprovalHis obah);
}
