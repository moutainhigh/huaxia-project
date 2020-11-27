package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasFicoMsgDao {

	List<String> queryFicoQueryMsg(String appId);
	
	List<Map<String, Object>> queryDateAndCountLimit();
	
	int queryFicoCount();
	
	List<String> queryFicoMsg();

	List<Map<String, String>> queryRuleContent();
	
	List<String> queryPbocBadness(String appId);
	
}