package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.Opasrevieentrycompareinfo;

public interface OpasrevieentrycompareinfoDao {

    int deleteByPrimaryKey(String appId);

    int insert(Opasrevieentrycompareinfo record);


    List<Opasrevieentrycompareinfo> selectByExample(Opasrevieentrycompareinfo example);

    Opasrevieentrycompareinfo selectByPrimaryKey(String appId);

    Map<String,String> selectSignFullInfo(String appId);
}