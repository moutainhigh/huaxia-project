package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.QHZXMsc8037Dao;
import com.huaxia.opas.domain.QHZXMsc8037;
import com.huaxia.opas.mapper.QHZXMsc8037Mapper;

@Repository
public class QHZXMsc8037DaoImpl implements QHZXMsc8037Dao {
	
	@Resource
	private QHZXMsc8037Mapper msc8037Mapper;


	public QHZXMsc8037Mapper getMsc8037Mapper() {
		return msc8037Mapper;
	}

	public void setMsc8037Mapper(QHZXMsc8037Mapper msc8037Mapper) {
		this.msc8037Mapper = msc8037Mapper;
	}

	@Override
	public int insert(QHZXMsc8037 msc8037) {
		return getMsc8037Mapper().insert(msc8037);
	}
	@Override
	public int selectCountMsc8037ByAppId(String appId) {
		return getMsc8037Mapper().selectCountMsc8037ByAppId(appId);
	}
}
