package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasFqzResultDao {

	List<Map<String, String>> selectFqzResultByAppId(String appId);
	
	List<String> selectFqzIdStatusByAppId(String appId);
	
	List<String> selectFqzDecisionLvByAppId(String appId);

	List<String> selectCustRiskMsg(String c1Idnbr);

	List<String> selectInNetTimeTypeByAppId(String appId);

	List<String> selectFqzWsScoreByAppId(String appId);
	
	List<String> selectFqzFwsScoreByAppId(String appId);

}