package com.huaxia.opas.dao.rule;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.huaxia.opas.domain.rule.Opasbizspeciallistiddata;

public interface OpasbizspeciallistiddataDao {

    int insert(Opasbizspeciallistiddata record);


    List<Opasbizspeciallistiddata> selectByExample(Opasbizspeciallistiddata example);
    List<Opasbizspeciallistiddata> selectByAppid(String  appId);

}