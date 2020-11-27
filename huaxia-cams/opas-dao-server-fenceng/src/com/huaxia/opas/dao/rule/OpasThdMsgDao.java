package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasThdMsgDao {

	List<Map<String, Object>> queryDateAndCountLimit(String limitModuleType);
	
	int queryTxyysCurrentCount(Map<String, Object> map);

	int queryPhoneCurrentCount(Map<String, Object> map);
	
}