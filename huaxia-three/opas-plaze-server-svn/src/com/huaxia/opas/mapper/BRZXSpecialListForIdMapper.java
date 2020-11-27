package com.huaxia.opas.mapper;

import com.huaxia.opas.domain.BRZXSpecialList;

public interface BRZXSpecialListForIdMapper {

	int insert(BRZXSpecialList specialList);
	
	int selectAllTaskCountByAppId(String appId);
	
}
