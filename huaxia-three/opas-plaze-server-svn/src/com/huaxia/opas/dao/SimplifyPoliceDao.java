package com.huaxia.opas.dao;

import com.huaxia.opas.domain.SimplifyPolice;

public interface SimplifyPoliceDao {

	int insert(SimplifyPolice police);
	
	int selectCountByAppId(String appId);
	
	int selectCountByCertNo(String certNo);
	
}
