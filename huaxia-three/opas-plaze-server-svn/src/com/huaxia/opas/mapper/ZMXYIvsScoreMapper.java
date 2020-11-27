package com.huaxia.opas.mapper;

import com.huaxia.opas.domain.ZMXYIvsScore;

public interface ZMXYIvsScoreMapper {

	int insert(ZMXYIvsScore ivsScore);
	
	int selectCountZMXYIvsScoreByAppId(String appId);
}
