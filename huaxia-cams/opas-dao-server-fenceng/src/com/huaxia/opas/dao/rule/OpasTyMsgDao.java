package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasTyMsgDao {

	int queryTyScore(String appId);
	
	List<Map<String,String>> queryTyScoreCodeFound(String appId);
	
	List<Map<String, String>> queryTyRiskLv(String appId);
	
}