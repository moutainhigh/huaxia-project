package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXSpecialListForIdDao;
import com.huaxia.opas.domain.BRZXSpecialList;
import com.huaxia.opas.mapper.BRZXSpecialListForIdMapper;

@Repository
public class BRZXSpecialListForIdDaoImpl implements BRZXSpecialListForIdDao {

	@Resource
	private BRZXSpecialListForIdMapper specialListForIdMapper;

	public BRZXSpecialListForIdMapper getSpecialListForIdMapper() {
		return specialListForIdMapper;
	}

	public void setSpecialListForIdMapper(BRZXSpecialListForIdMapper specialListForIdMapper) {
		this.specialListForIdMapper = specialListForIdMapper;
	}
	
	@Override
	public int insert(BRZXSpecialList specialListForId) {
		return getSpecialListForIdMapper().insert(specialListForId);
	}


	@Override
	public int selectAllTaskCountByAppId(String appId) {
		return getSpecialListForIdMapper().selectAllTaskCountByAppId(appId);
	}

}
