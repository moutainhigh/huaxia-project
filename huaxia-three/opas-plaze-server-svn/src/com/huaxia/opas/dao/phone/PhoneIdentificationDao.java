package com.huaxia.opas.dao.phone;

import com.huaxia.opas.domain.phone.PhoneIdentification;

public interface PhoneIdentificationDao {
	
	int getCountByAppId(String appId);
	
	int insert(PhoneIdentification phoneIdentification);

}
