package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXCreditPointDao;
import com.huaxia.opas.domain.BRZXCreditPoint;
import com.huaxia.opas.mapper.BRZXCreditPointMapper;

@Repository
public class BRZXCreditPointDaoImpl implements BRZXCreditPointDao {

	@Resource
	private BRZXCreditPointMapper creditPointMapper;

	@Override
	public int insert(BRZXCreditPoint creditPoint) {
		return getCreditPointMapper().insert(creditPoint);
	}

	public BRZXCreditPointMapper getCreditPointMapper() {
		return creditPointMapper;
	}

	public void setCreditPointMapper(BRZXCreditPointMapper creditPointMapper) {
		this.creditPointMapper = creditPointMapper;
	}

}
