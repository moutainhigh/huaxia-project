package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.ZMXYScoreDao;
import com.huaxia.opas.domain.ZMXYScore;
import com.huaxia.opas.mapper.ZMXYScoreMapper;

@Repository
public class ZMXYScoreDaoImpl implements ZMXYScoreDao {

	@Resource
	private ZMXYScoreMapper scoreMapper;
	
	public ZMXYScoreMapper getScoreMapper() {
		return scoreMapper;
	}

	public void setScoreMapper(ZMXYScoreMapper scoreMapper) {
		this.scoreMapper = scoreMapper;
	}
	
	@Override
	public int insert(ZMXYScore score) {
		return getScoreMapper().insert(score);
	}

	@Override
	public int selectCountZMXYScoreByAppId(String appId) {
		return getScoreMapper().selectCountZMXYScoreByAppId(appId);
	}
}
