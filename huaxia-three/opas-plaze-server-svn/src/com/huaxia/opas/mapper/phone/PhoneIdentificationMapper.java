package com.huaxia.opas.mapper.phone;

import com.huaxia.opas.domain.phone.PhoneIdentification;

public interface PhoneIdentificationMapper {
	
	int getCountByAppId(String appId);
	
	int insert(PhoneIdentification phoneIdentification);

}
