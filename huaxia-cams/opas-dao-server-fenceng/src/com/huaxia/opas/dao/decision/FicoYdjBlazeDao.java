package com.huaxia.opas.dao.decision;

import com.huaxia.opas.domain.input.FicoYdjBlaze;

public interface FicoYdjBlazeDao {
    int deleteByPrimaryKey(String ydjId);

    int insert(FicoYdjBlaze record);

    int insertSelective(FicoYdjBlaze record);

    FicoYdjBlaze selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(FicoYdjBlaze record);

    int updateByPrimaryKey(FicoYdjBlaze record);

	FicoYdjBlaze selectByAppId(String appId);

	FicoYdjBlaze selectSystemDecisionByAppId(String appId);

}