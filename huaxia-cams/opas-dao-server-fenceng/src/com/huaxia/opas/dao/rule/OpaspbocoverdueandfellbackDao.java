package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opaspbocoverdueandfellback;

public interface OpaspbocoverdueandfellbackDao {

    int insert(Opaspbocoverdueandfellback record);


    List<Opaspbocoverdueandfellback> selectByExample(Opasbizinpapplc1 appId);
}