package com.huaxia.opas.dao.rule;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.huaxia.opas.domain.rule.Opasbizapprovainfosupp;

public interface OpasbizapprovainfosuppDao {

    int deleteByPrimaryKey(String insideAppNo);

    int insert(Opasbizapprovainfosupp record);

    List<Opasbizapprovainfosupp> selectByExample(String appId);

    Opasbizapprovainfosupp selectByPrimaryKey(String insideAppNo);

}