package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXBrCreditPointDao;
import com.huaxia.opas.domain.BRZXBrCreditPoint;
import com.huaxia.opas.mapper.BRZXBrCreditPointMapper;

@Repository
public class BRZXBrCreditPointDaoImpl implements BRZXBrCreditPointDao {

	@Resource
	private BRZXBrCreditPointMapper brCreditPointMapper;

	@Override
	public int insert(BRZXBrCreditPoint brCreditPoint) {
		return getBrCreditPointMapper().insert(brCreditPoint);
	}

	public BRZXBrCreditPointMapper getBrCreditPointMapper() {
		return brCreditPointMapper;
	}

	public void setBrCreditPointMapper(BRZXBrCreditPointMapper brCreditPointMapper) {
		this.brCreditPointMapper = brCreditPointMapper;
	}

}
