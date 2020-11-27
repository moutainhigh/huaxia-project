package com.huaxia.opas.dao.impl.phone;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.phone.PhoneIdentificationDao;
import com.huaxia.opas.domain.phone.PhoneIdentification;
import com.huaxia.opas.mapper.phone.PhoneIdentificationMapper;

@Repository
public class PhoneIdentificationDaoImpl implements PhoneIdentificationDao {
	
	@Resource
	PhoneIdentificationMapper phoneIdentificationMapper;

	@Override
	public int getCountByAppId(String appId) {
		return phoneIdentificationMapper.getCountByAppId(appId);
	}

	@Override
	public int insert(PhoneIdentification phoneIdentification) {
		return phoneIdentificationMapper.insert(phoneIdentification);
	}

}
