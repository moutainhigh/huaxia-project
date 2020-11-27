package com.huaxia.opas.dao.rule;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.huaxia.opas.domain.rule.Opasbizspeciallistcelldata;

public interface OpasbizspeciallistcelldataDao {

    int insert(Opasbizspeciallistcelldata record);


    List<Opasbizspeciallistcelldata> selectByExample(Opasbizspeciallistcelldata example);
    List<Opasbizspeciallistcelldata> selectByAppid(String appId);

}