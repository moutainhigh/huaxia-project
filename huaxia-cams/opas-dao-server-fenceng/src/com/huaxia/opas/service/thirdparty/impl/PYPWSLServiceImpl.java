package com.huaxia.opas.service.thirdparty.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.huaxia.opas.dao.thirdparty.PYPWSLDao;
import com.huaxia.opas.service.thirdparty.PYPWSLService;

public class PYPWSLServiceImpl implements PYPWSLService {

	@Autowired
	public PYPWSLDao pypwslDao;
	
	@Override
	public Map<String, Object> queryAllPYPWSLInfo(String appId) {
		return pypwslDao.selectAllPYPWSLInfo(appId);
	}

}
