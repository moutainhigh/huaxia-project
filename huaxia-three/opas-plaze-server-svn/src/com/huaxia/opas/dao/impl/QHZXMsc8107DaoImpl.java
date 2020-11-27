package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.QHZXMsc8107Dao;
import com.huaxia.opas.domain.QHZXMsc8107;
import com.huaxia.opas.mapper.QHZXMsc8107Mapper;

@Repository
public class QHZXMsc8107DaoImpl implements QHZXMsc8107Dao {
	
	@Resource
	private QHZXMsc8107Mapper msc8107Mapper;


	public QHZXMsc8107Mapper getMsc8107Mapper() {
		return msc8107Mapper;
	}

	public void setMsc8107Mapper(QHZXMsc8107Mapper msc8107Mapper) {
		this.msc8107Mapper = msc8107Mapper;
	}
	@Override
	public int insert(QHZXMsc8107 msc8107) {
		return getMsc8107Mapper().insert(msc8107);
	}

	@Override
	public int selectCountMsc8107ByAppId(String appId) {
		return getMsc8107Mapper().selectCountMsc8107ByAppId(appId);
	}
}
