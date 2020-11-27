package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.huaxia.opas.dao.thirdparty.HzGjjDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.HzGjjService;

public class HzGjjServiceImpl extends AbstractService implements HzGjjService, Serializable{

	private static final long serialVersionUID = -5160499553814471434L;

	@Autowired
	public HzGjjDao hzGjjDao;
	
	@Override
	public List<Map<String, String>> queryHzVehiclePenaltyInfo(String appId) {
		return hzGjjDao.queryHzVehiclePenaltyInfo(appId);
	}

	@Override
	public List<Map<String, String>> queryHzVehicleInfo(String appId) {
		return hzGjjDao.queryHzVehicleInfo(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjLoadBrief(String appId) {
		return hzGjjDao.queryGjjLoadBrief(appId);
	}

	@Override
	public List<Map<String, String>> queryHzFyNaturalPerson(String appId) {
		return hzGjjDao.queryHzFyNaturalPerson(appId);
	}

	@Override
	public List<Map<String, String>> queryHzWaterAffairsInfo(String appId) {
		return hzGjjDao.queryHzWaterAffairsInfo(appId);
	}

	@Override
	public List<Map<String, String>> queryHzRsjZxAc01(String appId) {
		return hzGjjDao.queryHzRsjZxAc01(appId);
	}

	@Override
	public List<Map<String, String>> querySBfeeinfogridInfo(String appId) {
		return hzGjjDao.querySBfeeinfogridInfo(appId);
	}

	@Override
	public Map<String, String> queryGrxxInfo(String appId) {
		return hzGjjDao.queryGrxxInfo(appId);
	}

	@Override
	public List<Map<String, String>> queryGjjxxInfo(String appId) {
		return hzGjjDao.queryGjjxxInfo(appId);
	}


}
