package com.huaxia.cams.modules.unicom.mapper;

import com.huaxia.cams.modules.unicom.domain.UnicomOnline;

public interface UnicomOnlineMapper {
	
	int selectCountByAppId(String appId);
	
	void insertUnicomOnline(UnicomOnline unicomOnline);

}
