package com.huaxia.opas.dao;

import com.huaxia.opas.domain.BRZXSpecialList;

public interface BRZXSpecialListForIdDao {

	int insert(BRZXSpecialList specialList);
	
	int selectAllTaskCountByAppId(String appId);
	
}
