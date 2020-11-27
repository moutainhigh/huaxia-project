package com.huaxia.opas.dao;

import java.util.Map;

public interface PoliceGatherDao {

	int insert(Map<String, Object> entity);
	
	int insertFailure(Map<String, Object> entity);
	
	int selectCountByAppId(String appId);
	
	int selectCountByCertNo(String certNo);
	
}
