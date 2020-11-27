package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opaspbocqueryreclmrecsum;

public interface OpaspbocqueryreclmrecsumDao {

    int insert(Opaspbocqueryreclmrecsum record);


    List<Opaspbocqueryreclmrecsum> selectByExample(Opasbizinpapplc1 example);
}