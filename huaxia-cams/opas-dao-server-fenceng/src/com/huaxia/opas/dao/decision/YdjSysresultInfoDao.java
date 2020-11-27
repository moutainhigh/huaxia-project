package com.huaxia.opas.dao.decision;

import com.huaxia.opas.domain.decision.YdjSysresultInfo;

public interface YdjSysresultInfoDao {
	
	int deleteByPrimaryKey(String autoId);

	int insert(YdjSysresultInfo record);

	int insertSelective(YdjSysresultInfo record);

	YdjSysresultInfo selectByPrimaryKey(String autoId);
	
	public YdjSysresultInfo selectCreditScore(String appId);
	
	int updateByPrimaryKeySelective(YdjSysresultInfo record);

	int updateByPrimaryKey(YdjSysresultInfo record);

	YdjSysresultInfo selectSysResultfoByAppId(String appId);
	
	// 2017/06/02 from wjf 征信评分
	public Float selectCreditScoreYdj(String appId);
	
}