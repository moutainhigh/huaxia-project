package com.huaxia.opas.mapper;

import com.huaxia.opas.domain.ZMXYScore;

public interface ZMXYScoreMapper {

	int insert(ZMXYScore score);
	
	int selectCountZMXYScoreByAppId(String appId);
}
