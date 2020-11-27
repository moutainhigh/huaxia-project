package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.VerificationRequestCheckDao;
import com.huaxia.opas.domain.VerificationRequestCheck;
import com.huaxia.opas.service.VerificationRequestCheckService;

@Service("verificationRequestCheckService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class VerificationRequestCheckServiceImpl implements VerificationRequestCheckService {
	
	@Autowired
	private VerificationRequestCheckDao verificationRequestCheckDao;

	@Override
	public VerificationRequestCheck get() {
		return getVerificationRequestCheckDao().select();
	}
	
	@Override
	public int update() {
		return getVerificationRequestCheckDao().update();
	}

	public VerificationRequestCheckDao getVerificationRequestCheckDao() {
		return verificationRequestCheckDao;
	}

	public void setVerificationRequestCheckDao(VerificationRequestCheckDao verificationRequestCheckDao) {
		this.verificationRequestCheckDao = verificationRequestCheckDao;
	}

}
