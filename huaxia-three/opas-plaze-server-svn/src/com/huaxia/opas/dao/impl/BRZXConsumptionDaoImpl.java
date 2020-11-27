package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXConsumptionDao;
import com.huaxia.opas.domain.BRZXConsumption;
import com.huaxia.opas.mapper.BRZXConsumptionMapper;

@Repository
public class BRZXConsumptionDaoImpl implements BRZXConsumptionDao {
	
	@Resource
	private BRZXConsumptionMapper consumptionMapper;
	
	@Override
	public int insert(BRZXConsumption consumption) {
		return getConsumptionMapper().insert(consumption);
	}

	public BRZXConsumptionMapper getConsumptionMapper() {
		return consumptionMapper;
	}

	public void setConsumptionMapper(BRZXConsumptionMapper consumptionMapper) {
		this.consumptionMapper = consumptionMapper;
	}

}
