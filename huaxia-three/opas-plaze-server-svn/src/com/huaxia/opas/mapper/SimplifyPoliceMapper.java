package com.huaxia.opas.mapper;

import com.huaxia.opas.domain.SimplifyPolice;

public interface SimplifyPoliceMapper {

	int insert(SimplifyPolice police);
	
	int selectCountByAppId(String appId);
	
	int selectCountByCertNo(String certNo);
	
}
