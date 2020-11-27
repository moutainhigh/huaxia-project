package com.huaxia.opas.service.thirdparty.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.huaxia.opas.dao.thirdparty.YLZDao;
import com.huaxia.opas.service.thirdparty.YLZService;

public class YLZServiceImpl implements YLZService {
	
	@Autowired
	public YLZDao ylzDao;

	@Override
	public Map<String, String> queryAllInfo(String appId) {
		return ylzDao.selectAllYLZInfo(appId);
	}

	@Override
	public int queryCount(String appId) {
		return ylzDao.selectCount(appId);
	}

	@Override
	public Map<String, String> queryHzGjjxxInfo(String appId) {
		return ylzDao.queryHzGjjxxInfo(appId);
	}

	@Override
	public Map<String, String> queryXMPersonal(String appId) {
		return ylzDao.queryXMPersonal(appId);
	}

	@Override
	public Map<String, String> queryXmGjjInfo(String appId) {
		return ylzDao.queryXmGjjInfo(appId);
	}

	@Override
	public Map<String, String> queryWzgjjInfo(String appId) {
		return ylzDao.queryWzgjjInfo(appId);
	}

	@Override
	public Map<String, String> queryYcInfo(String appId) {
		return ylzDao.queryYcInfo(appId);
	}
}
