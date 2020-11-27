package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opaspbocgrurantee;
public interface OpaspbocgruranteeDao {

    int insert(Opaspbocgrurantee record);


    List<Opaspbocgrurantee> selectByExample(String example);

}