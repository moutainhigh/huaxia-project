package com.huaxia.opas.dao;

import com.huaxia.opas.domain.ZMXYScore;

public interface ZMXYScoreDao {

	int insert(ZMXYScore score);
	
	int selectCountZMXYScoreByAppId(String appId);
}
