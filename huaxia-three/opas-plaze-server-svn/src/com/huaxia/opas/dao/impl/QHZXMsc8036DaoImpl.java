package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.QHZXMsc8036Dao;
import com.huaxia.opas.domain.QHZXMsc8036;
import com.huaxia.opas.mapper.QHZXMsc8036Mapper;

@Repository
public class QHZXMsc8036DaoImpl implements QHZXMsc8036Dao {
	
	@Resource
	private QHZXMsc8036Mapper msc8036Mapper;


	public QHZXMsc8036Mapper getMsc8036Mapper() {
		return msc8036Mapper;
	}

	public void setMsc8036Mapper(QHZXMsc8036Mapper msc8036Mapper) {
		this.msc8036Mapper = msc8036Mapper;
	}
	@Override
	public int insert(QHZXMsc8036 msc8036) {
		return getMsc8036Mapper().insert(msc8036);
	}

	@Override
	public int selectCountMsc8036ByAppId(String appId) {
		return getMsc8036Mapper().selectCountMsc8036ByAppId(appId);
	}
}
