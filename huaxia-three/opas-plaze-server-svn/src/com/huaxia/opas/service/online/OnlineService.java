package com.huaxia.opas.service.online;

import com.huaxia.opas.domain.online.Online;

public interface OnlineService {
	
	int getCountByAppId(String appId);
	
	int insert(Online online);

}
