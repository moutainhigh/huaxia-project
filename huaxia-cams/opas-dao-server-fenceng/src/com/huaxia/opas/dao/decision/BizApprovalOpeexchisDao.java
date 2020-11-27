package com.huaxia.opas.dao.decision;

import com.huaxia.opas.domain.input.BizApprovalOpeexchis;

public interface BizApprovalOpeexchisDao {
    int deleteByPrimaryKey(String id);

    int insert(BizApprovalOpeexchis record);

    int insertSelective(BizApprovalOpeexchis record);

    BizApprovalOpeexchis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BizApprovalOpeexchis record);

    int updateByPrimaryKey(BizApprovalOpeexchis record);

	BizApprovalOpeexchis selectByUserCode(String userCode);
}