package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.QHZXMsc8007Dao;
import com.huaxia.opas.domain.QHZXMsc8007;
import com.huaxia.opas.mapper.QHZXMsc8007Mapper;

@Repository
public class QHZXMsc8007DaoImpl implements QHZXMsc8007Dao {
	
	@Resource
	private QHZXMsc8007Mapper msc8007Mapper;


	public QHZXMsc8007Mapper getMsc8007Mapper() {
		return msc8007Mapper;
	}

	public void setMsc8007Mapper(QHZXMsc8007Mapper msc8007Mapper) {
		this.msc8007Mapper = msc8007Mapper;
	}
	@Override
	public int insert(QHZXMsc8007 msc8007) {
		return getMsc8007Mapper().insert(msc8007);
	}

	@Override
	public int selectCountMsc8007ByAppId(String appId) {
		return getMsc8007Mapper().selectCountMsc8007ByAppId(appId);
	}
}
