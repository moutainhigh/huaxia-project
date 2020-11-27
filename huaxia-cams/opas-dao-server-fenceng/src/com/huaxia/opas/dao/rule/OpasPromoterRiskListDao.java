package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.OpasPromoterRiskList;

public interface OpasPromoterRiskListDao {

    int deleteByPrimaryKey(String autoId);

    int insert(OpasPromoterRiskList record);


    List<OpasPromoterRiskList> selectByExample(OpasPromoterRiskList example);
    List<OpasPromoterRiskList> selectByPromoterNo(String promoterNo);

    OpasPromoterRiskList selectByPrimaryKey(String autoId);

}