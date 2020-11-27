package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizinpapplc2;

public interface Opasbizinpapplc2Dao {

    int deleteByPrimaryKey(String insideAppNo);

    int insert(Opasbizinpapplc2 record);

    List<Opasbizinpapplc2> selectByExample(Opasbizinpapplc2 example);

    Opasbizinpapplc2 selectByPrimaryKey(String insideAppNo);
}