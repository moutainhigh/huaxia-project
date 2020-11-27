package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.QHZXMsc8075Dao;
import com.huaxia.opas.domain.QHZXMsc8075;
import com.huaxia.opas.service.QHZXMsc8075Service;

@Service("msc8075Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QHZXMsc8075ServiceImpl implements QHZXMsc8075Service {

	@Autowired
	private QHZXMsc8075Dao msc8075Dao;

	public QHZXMsc8075Dao getMsc8075Dao() {
		return msc8075Dao;
	}

	public void setMsc8075Dao(QHZXMsc8075Dao msc8075Dao) {
		this.msc8075Dao = msc8075Dao;
	}

	@Override
	public int save(QHZXMsc8075 msc8075) {
		return getMsc8075Dao().insert(msc8075);
	}

	@Override
	public int queryCountMsc8075ByAppId(String appId) {
		return getMsc8075Dao().selectCountMsc8075ByAppId(appId);
	}
}
