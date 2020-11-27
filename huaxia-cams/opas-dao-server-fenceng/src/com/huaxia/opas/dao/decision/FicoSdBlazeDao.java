package com.huaxia.opas.dao.decision;

import java.util.List;

import com.huaxia.opas.domain.input.FicoSdBlaze;

public interface FicoSdBlazeDao {
    int deleteByPrimaryKey(String sdId);

    int insert(FicoSdBlaze record);

    int insertSelective(FicoSdBlaze record);

    FicoSdBlaze selectByPrimaryKey(String sdId);

    int updateByPrimaryKeySelective(FicoSdBlaze record);

    int updateByPrimaryKey(FicoSdBlaze record);

	FicoSdBlaze selectByAppId(String appId);
	FicoSdBlaze selectByAppIdSea(String appId);

	FicoSdBlaze selectSystemDecisionByAppId(String appId);
	
	List<String> selectGxkjCity();
}