package com.huaxia.opas.service.phone;

import com.huaxia.opas.domain.phone.PhoneIdentification;

public interface PhoneIdentificationService {
	
	int getCountByAppId(String appId);
	
	int insert(PhoneIdentification phoneIdentification);

}
