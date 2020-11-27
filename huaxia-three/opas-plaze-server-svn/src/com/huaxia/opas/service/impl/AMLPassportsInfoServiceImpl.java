package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLPassportsInfoDao;
import com.huaxia.opas.domain.AMLPassportsInfo;
import com.huaxia.opas.service.AMLPassportsInfoService;

@Service("passportsInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLPassportsInfoServiceImpl implements AMLPassportsInfoService {

	@Autowired
	private AMLPassportsInfoDao passportsInfoDao;

	@Override
	public int save(AMLPassportsInfo passportsInfo) {
		return getPassportsInfoDao().insert(passportsInfo);
	}

	public AMLPassportsInfoDao getPassportsInfoDao() {
		return passportsInfoDao;
	}

	public void setPassportsInfoDao(AMLPassportsInfoDao passportsInfoDao) {
		this.passportsInfoDao = passportsInfoDao;
	}

}
