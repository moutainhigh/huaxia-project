package com.huaxia.opas.dao;

import com.huaxia.opas.domain.ZMXYIvsScore;

public interface ZMXYIvsScoreDao {

	int insert(ZMXYIvsScore ivsScore);
	
	int selectCountZMXYIvsScoreByAppId(String appId);
}
