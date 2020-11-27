package com.huaxia.opas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.ZMXYIvsScoreDao;
import com.huaxia.opas.dao.ZMXYScoreDao;
import com.huaxia.opas.dao.ZMXYWatchList2Dao;
import com.huaxia.opas.domain.ZMXYScore;
import com.huaxia.opas.service.ZMXYScoreService;

@Service("scoreService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ZMXYScoreServiceImpl  implements ZMXYScoreService {
	private final static Logger logger = LoggerFactory.getLogger(ZMXYScoreServiceImpl.class);
	@Autowired
	private ZMXYScoreDao scoreDao;

	public ZMXYScoreDao getScoreDao() {
		return scoreDao;
	}

	public void setScoreDao(ZMXYScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}
	@Autowired
	private ZMXYIvsScoreDao ivsScoreDao;
	public ZMXYIvsScoreDao getIvsScoreDao() {
		return ivsScoreDao;
	}
	public void setIvsScoreDao(ZMXYIvsScoreDao ivsScoreDao) {
		this.ivsScoreDao = ivsScoreDao;
	}
	@Autowired
	private ZMXYWatchList2Dao watchList2Dao;

	public ZMXYWatchList2Dao getWatchList2Dao() {
		return watchList2Dao;
	}
	public void setWatchList2Dao(ZMXYWatchList2Dao watchList2Dao) {
		this.watchList2Dao = watchList2Dao;
	}
	
	@Override
	public int save(ZMXYScore score) {
		return getScoreDao().insert(score);
	}

	@Override
	public int queryCountZMXYScoreByAppId(String appId) {
		return getScoreDao().selectCountZMXYScoreByAppId(appId);
	}
	
}