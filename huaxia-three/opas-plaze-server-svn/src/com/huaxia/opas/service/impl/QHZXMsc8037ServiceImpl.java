package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.QHZXMsc8037Dao;
import com.huaxia.opas.domain.QHZXMsc8037;
import com.huaxia.opas.service.QHZXMsc8037Service;

@Service("msc8037Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class QHZXMsc8037ServiceImpl implements QHZXMsc8037Service {

	@Autowired
	private QHZXMsc8037Dao msc8037Dao;

	public QHZXMsc8037Dao getMsc8037Dao() {
		return msc8037Dao;
	}

	public void setMsc8037Dao(QHZXMsc8037Dao msc8037Dao) {
		this.msc8037Dao = msc8037Dao;
	}

	@Override
	public int save(QHZXMsc8037 msc8037) {
		return getMsc8037Dao().insert(msc8037);
	}

	@Override
	public int queryCountMsc8037ByAppId(String appId) {
		return getMsc8037Dao().selectCountMsc8037ByAppId(appId);
	}
}
