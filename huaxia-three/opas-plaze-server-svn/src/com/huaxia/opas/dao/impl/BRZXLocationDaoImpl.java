package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXLocationDao;
import com.huaxia.opas.domain.BRZXLocation;
import com.huaxia.opas.mapper.BRZXLocationMapper;

@Repository
public class BRZXLocationDaoImpl implements BRZXLocationDao {

	@Resource
	private BRZXLocationMapper locationMapper;

	@Override
	public int insert(BRZXLocation location) {
		return getLocationMapper().insert(location);
	}

	public BRZXLocationMapper getLocationMapper() {
		return locationMapper;
	}

	public void setLocationMapper(BRZXLocationMapper locationMapper) {
		this.locationMapper = locationMapper;
	}

}
