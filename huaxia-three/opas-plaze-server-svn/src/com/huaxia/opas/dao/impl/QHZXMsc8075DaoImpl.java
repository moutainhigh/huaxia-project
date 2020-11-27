package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.QHZXMsc8075Dao;
import com.huaxia.opas.domain.QHZXMsc8075;
import com.huaxia.opas.mapper.QHZXMsc8075Mapper;

@Repository
public class QHZXMsc8075DaoImpl implements QHZXMsc8075Dao {
	
	@Resource
	private QHZXMsc8075Mapper msc8075Mapper;


	public QHZXMsc8075Mapper getMsc8075Mapper() {
		return msc8075Mapper;
	}

	public void setMsc8075Mapper(QHZXMsc8075Mapper msc8075Mapper) {
		this.msc8075Mapper = msc8075Mapper;
	}
	@Override
	public int insert(QHZXMsc8075 msc8075) {
		return getMsc8075Mapper().insert(msc8075);
	}

	@Override
	public int selectCountMsc8075ByAppId(String appId) {
		return getMsc8075Mapper().selectCountMsc8075ByAppId(appId);
	}
}
