package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.OpasBizApprovalHis;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
public interface OpasBizApprovalHisDao {
    int deleteByPrimaryKey(String appId);

    int insert(OpasBizApprovalHis record);

    List<OpasBizApprovalHis> selectByExample(Opasbizinpapplc1 example);

    OpasBizApprovalHis selectByPrimaryKey(String appId);
}