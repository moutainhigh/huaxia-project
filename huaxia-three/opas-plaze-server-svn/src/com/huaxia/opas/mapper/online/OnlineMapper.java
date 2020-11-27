package com.huaxia.opas.mapper.online;

import com.huaxia.opas.domain.online.Online;

public interface OnlineMapper {
	
	int getCountByAppId(String appId);
	
	int insert(Online online);

}
