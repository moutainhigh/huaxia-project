package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opasbizzmscordata;

public interface OpasbizzmscordataDao {

    int insert(Opasbizzmscordata record);

    List<Opasbizzmscordata> selectByExample(Opasbizinpapplc1 example);

}