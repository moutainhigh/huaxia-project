package com.huaxia.opas.mapper;

import java.util.Map;

public interface PoliceGatherMapper {

	int insert(Map<String, Object> entity);
	
	int insertFailure(Map<String, Object> entity);
	
	int selectCountByAppId(String appId);
	
	int selectCountByCertNo(String certNo);
	
}
