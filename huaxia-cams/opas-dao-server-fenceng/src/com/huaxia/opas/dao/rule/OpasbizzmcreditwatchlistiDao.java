package com.huaxia.opas.dao.rule;

import java.util.List;

import com.huaxia.opas.domain.rule.Opasbizzmcreditwatchlisti;

public interface OpasbizzmcreditwatchlistiDao {

    int insert(Opasbizzmcreditwatchlisti record);


    List<Opasbizzmcreditwatchlisti> selectByExample(Opasbizzmcreditwatchlisti example);
    List<Opasbizzmcreditwatchlisti> selectByAppId(String appId);

	List<String> selectAntHitMsg(String appId);

}