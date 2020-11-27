package com.huaxia.opas.dao.online;

import com.huaxia.opas.domain.online.Online;

public interface OnlineDao {
	
	int getCountByAppId(String appId);
	
	int insert(Online online);

}
