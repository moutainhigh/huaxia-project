package com.huaxia.opas.dao.decision;

import com.huaxia.opas.domain.thirdparty.StockcustTelsaleList;

public interface StockcustTelsaleListDao {
    int deleteByPrimaryKey(String autoId);

    int insert(StockcustTelsaleList record);

    int insertSelective(StockcustTelsaleList record);

    StockcustTelsaleList selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(StockcustTelsaleList record);

    int updateByPrimaryKey(StockcustTelsaleList record);
}