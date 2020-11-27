package com.huaxia.opas.service.impl.phone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.phone.PhoneIdentificationDao;
import com.huaxia.opas.domain.phone.PhoneIdentification;
import com.huaxia.opas.service.phone.PhoneIdentificationService;

@Service("phoneIdentificationService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PhoneIdentificationServiceImpl implements PhoneIdentificationService {
	
	private final static Logger logger = LoggerFactory.getLogger(PhoneIdentificationServiceImpl.class);
	
	@Autowired
	private PhoneIdentificationDao phoneIdentificationDao;

	public PhoneIdentificationDao getPhoneIdentificationDao() {
		return phoneIdentificationDao;
	}

	public void setPhoneIdentificationDao(PhoneIdentificationDao phoneIdentificationDao) {
		this.phoneIdentificationDao = phoneIdentificationDao;
	}

	@Override
	public int getCountByAppId(String appId) {
		return phoneIdentificationDao.getCountByAppId(appId);
	}

	@Override
	public int insert(PhoneIdentification phoneIdentification) {
		return phoneIdentificationDao.insert(phoneIdentification);
	}
	
	
	
}
