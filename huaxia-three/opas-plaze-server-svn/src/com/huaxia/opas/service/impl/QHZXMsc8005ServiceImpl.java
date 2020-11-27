package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.QHZXMsc8005Dao;
import com.huaxia.opas.domain.QHZXMsc8005;
import com.huaxia.opas.service.QHZXMsc8005Service;

@Service("msc8005Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QHZXMsc8005ServiceImpl implements QHZXMsc8005Service {

	@Autowired
	private QHZXMsc8005Dao msc8005Dao;

	public QHZXMsc8005Dao getMsc8005Dao() {
		return msc8005Dao;
	}

	public void setMsc8005Dao(QHZXMsc8005Dao msc8005Dao) {
		this.msc8005Dao = msc8005Dao;
	}

	@Override
	public int save(QHZXMsc8005 msc8005) {
		return getMsc8005Dao().insert(msc8005);
	}

	@Override
	public int queryCountMsc8005ByAppId(String appId) {
		return getMsc8005Dao().selectCountMsc8005ByAppId(appId);
	}
}
