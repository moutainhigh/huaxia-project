package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.QHZXMsc8007Dao;
import com.huaxia.opas.domain.QHZXMsc8007;
import com.huaxia.opas.service.QHZXMsc8007Service;

@Service("msc8007Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QHZXMsc8007ServiceImpl implements QHZXMsc8007Service {

	@Autowired
	private QHZXMsc8007Dao msc8007Dao;

	public QHZXMsc8007Dao getMsc8007Dao() {
		return msc8007Dao;
	}

	public void setMsc8007Dao(QHZXMsc8007Dao msc8007Dao) {
		this.msc8007Dao = msc8007Dao;
	}

	@Override
	public int save(QHZXMsc8007 msc8007) {
		return getMsc8007Dao().insert(msc8007);
	}

	@Override
	public int queryCountMsc8007ByAppId(String appId) {
		return getMsc8007Dao().selectCountMsc8007ByAppId(appId);
	}
}
