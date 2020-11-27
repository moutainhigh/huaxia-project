package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.QHZXMsc8107Dao;
import com.huaxia.opas.domain.QHZXMsc8107;
import com.huaxia.opas.service.QHZXMsc8107Service;

@Service("msc8107Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QHZXMsc8107ServiceImpl implements QHZXMsc8107Service {

	@Autowired
	private QHZXMsc8107Dao msc8107Dao;

	public QHZXMsc8107Dao getMsc8107Dao() {
		return msc8107Dao;
	}

	public void setMsc8107Dao(QHZXMsc8107Dao msc8107Dao) {
		this.msc8107Dao = msc8107Dao;
	}

	@Override
	public int save(QHZXMsc8107 msc8107) {
		return getMsc8107Dao().insert(msc8107);
	}

	@Override
	public int queryCountMsc8107ByAppId(String appId) {
		return getMsc8107Dao().selectCountMsc8107ByAppId(appId);
	}
}
