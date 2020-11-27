package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PoliceGatherBlDao;
import com.huaxia.opas.mapper.PoliceGatherBlMapper;

@Repository
public class PoliceGatherBlDaoImpl implements PoliceGatherBlDao {
	
	@Resource
	private PoliceGatherBlMapper policeGatherBlMapper;

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getPoliceGatherBlMapper().insertBatch(parameters);
	}

	public PoliceGatherBlMapper getPoliceGatherBlMapper() {
		return policeGatherBlMapper;
	}

	public void setPoliceGatherBlMapper(PoliceGatherBlMapper policeGatherBlMapper) {
		this.policeGatherBlMapper = policeGatherBlMapper;
	}

}
