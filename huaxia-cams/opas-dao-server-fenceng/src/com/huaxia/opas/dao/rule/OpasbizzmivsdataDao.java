package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opasbizzmivsdata;

public interface OpasbizzmivsdataDao {

    int insert(Opasbizzmivsdata record);


    List<Opasbizzmivsdata> selectByExample(Opasbizinpapplc1 example);

}