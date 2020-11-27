package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.QHZXMsc8036Dao;
import com.huaxia.opas.domain.QHZXMsc8036;
import com.huaxia.opas.service.QHZXMsc8036Service;

@Service("msc8036Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QHZXMsc8036ServiceImpl implements QHZXMsc8036Service {

	@Autowired
	private QHZXMsc8036Dao msc8036Dao;

	public QHZXMsc8036Dao getMsc8036Dao() {
		return msc8036Dao;
	}

	public void setMsc8036Dao(QHZXMsc8036Dao msc8036Dao) {
		this.msc8036Dao = msc8036Dao;
	}

	@Override
	public int save(QHZXMsc8036 msc8036) {
		return getMsc8036Dao().insert(msc8036);
	}

	@Override
	public int queryCountMsc8036ByAppId(String appId) {
		return getMsc8036Dao().selectCountMsc8036ByAppId(appId);
	}
}
