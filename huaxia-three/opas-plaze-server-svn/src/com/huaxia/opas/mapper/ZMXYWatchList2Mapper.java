package com.huaxia.opas.mapper;

import java.util.Map;

import com.huaxia.opas.domain.ZMXYWatchList2;

public interface ZMXYWatchList2Mapper {

	int insert(ZMXYWatchList2 watchList2);
	
	int insertBatch(Map<String, Object> parameters);
	
	int selectCountZMXYWatchList2ByAppId(String appId);
}
