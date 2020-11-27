package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.ZMXYIvsScoreDao;
import com.huaxia.opas.domain.ZMXYIvsScore;
import com.huaxia.opas.mapper.ZMXYIvsScoreMapper;

@Repository
public class ZMXYIvsScoreDaoImpl implements ZMXYIvsScoreDao {

	@Resource
	private ZMXYIvsScoreMapper ivsScoreMapper;


	public ZMXYIvsScoreMapper getIvsScoreMapper() {
		return ivsScoreMapper;
	}

	public void setIvsScoreMapper(ZMXYIvsScoreMapper ivsScoreMapper) {
		this.ivsScoreMapper = ivsScoreMapper;
	}
	@Override
	public int insert(ZMXYIvsScore ivsScore) {
		return getIvsScoreMapper().insert(ivsScore);
	}

	@Override
	public int selectCountZMXYIvsScoreByAppId(String appId) {
		return getIvsScoreMapper().selectCountZMXYIvsScoreByAppId(appId);
	}
}
