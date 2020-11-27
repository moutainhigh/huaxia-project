package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.QHZXMsc8005Dao;
import com.huaxia.opas.domain.QHZXMsc8005;
import com.huaxia.opas.mapper.QHZXMsc8005Mapper;

@Repository
public class QHZXMsc8005DaoImpl implements QHZXMsc8005Dao {
	
	@Resource
	private QHZXMsc8005Mapper msc8005Mapper;

	public QHZXMsc8005Mapper getMsc8005Mapper() {
		return msc8005Mapper;
	}

	public void setMsc8005Mapper(QHZXMsc8005Mapper msc8005Mapper) {
		this.msc8005Mapper = msc8005Mapper;
	}
	
	@Override
	public int insert(QHZXMsc8005 msc8005) {
		return getMsc8005Mapper().insert(msc8005);
	}

	@Override
	public int selectCountMsc8005ByAppId(String appId) {
		return getMsc8005Mapper().selectCountMsc8005ByAppId(appId);
	}
}
