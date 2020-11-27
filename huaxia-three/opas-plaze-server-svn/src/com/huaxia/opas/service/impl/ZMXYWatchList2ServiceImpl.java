package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.ZMXYWatchList2Dao;
import com.huaxia.opas.domain.ZMXYWatchList2;
import com.huaxia.opas.service.ZMXYWatchList2Service;

@Service("watchList2Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ZMXYWatchList2ServiceImpl implements ZMXYWatchList2Service {

	@Autowired
	private ZMXYWatchList2Dao watchList2Dao;

	public ZMXYWatchList2Dao getWatchList2Dao() {
		return watchList2Dao;
	}
	public void setWatchList2Dao(ZMXYWatchList2Dao watchList2Dao) {
		this.watchList2Dao = watchList2Dao;
	}
	@Override
	public int save(ZMXYWatchList2 watchList2) {
		return getWatchList2Dao().insert(watchList2);
	}
	
	@Override
	public int saveBatch(List<ZMXYWatchList2> watchList2List) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", watchList2List);
		
		return getWatchList2Dao().insertBatch(parameters);
	}

	@Override
	public int queryCountZMXYWatchList2ByAppId(String appId) {

		return getWatchList2Dao().selectCountZMXYWatchList2ByAppId(appId);
	}

}
