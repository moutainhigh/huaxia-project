package com.huaxia.cams.modules.unicom.mapper;

import com.huaxia.cams.modules.unicom.domain.UnicomPhone;

public interface UnicomPhoneMapper {
	
	int selectCountByAppId(String appId);
	
	void insertUnicomPhone(UnicomPhone unicomPhone);

}
