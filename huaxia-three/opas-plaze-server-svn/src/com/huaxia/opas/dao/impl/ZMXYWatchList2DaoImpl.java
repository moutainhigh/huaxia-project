package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.ZMXYWatchList2Dao;
import com.huaxia.opas.domain.ZMXYWatchList2;
import com.huaxia.opas.mapper.ZMXYWatchList2Mapper;

@Repository
public class ZMXYWatchList2DaoImpl implements ZMXYWatchList2Dao {

	@Resource
	private ZMXYWatchList2Mapper watchList2Mapper;

	public ZMXYWatchList2Mapper getWatchList2Mapper() {
		return watchList2Mapper;
	}
	public void setWatchList2Mapper(ZMXYWatchList2Mapper watchList2Mapper) {
		this.watchList2Mapper = watchList2Mapper;
	}
	@Override
	public int insert(ZMXYWatchList2 watchList2) {
		return getWatchList2Mapper().insert(watchList2);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getWatchList2Mapper().insertBatch(parameters);
	}

	@Override
	public int selectCountZMXYWatchList2ByAppId(String appId) {

		return getWatchList2Mapper().selectCountZMXYWatchList2ByAppId(appId);
	}

}
