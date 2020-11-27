package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasThdCarMsgDao {

	List<Map<String, Object>> queryDateAndCountLimit();
	
	List<Map<String, Object>> queryTyDateAndCountLimit();
	
	int queryVehicleCount();
	
	int queryTyCount();
	
	List<String> queryVehicleMsg();

	List<Map<String, String>> queryRuleContent();
	
	List<Map<String, Object>> queryCompanyDateAndCountLimit();

	int queryCompanyCount();

	List<String> queryCompanyMsg();
}