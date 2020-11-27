package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.input.FicoSdBlaze;

public interface OpasFicoSdBlazeDao {
    int deleteByPrimaryKey(String sdId);

    int insert(FicoSdBlaze record);

    int insertSelective(FicoSdBlaze record);

    FicoSdBlaze selectByPrimaryKey(String sdId);

    int updateByPrimaryKeySelective(FicoSdBlaze record);

    int updateByPrimaryKey(FicoSdBlaze record);

	FicoSdBlaze selectByAppId(String appId);
	FicoSdBlaze selectByAppIdSea(String appId);

	FicoSdBlaze selectSystemDecisionByAppId(String appId);

	String selectCreateTime(String appId);
	
	List<Map<String,Object>> selectFicoMsgByAppId(String appId);
}