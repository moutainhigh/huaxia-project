package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.HzGjjDao;

public class HzGjjDaoImpl extends AbstractDAO implements HzGjjDao{

	private static final long serialVersionUID = 219077324710095099L;

	private static final String NAMESPACES = "HZGJJ.";
	
	@Override
	public Map<String, String> queryGrxxInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectGrxxInfo", appId);
	}

	@Override
	public List<Map<String, String>> querySBfeeinfogridInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectSBfeeinfogridInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryHzRsjZxAc01(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectHzRsjZxAc01", appId);
	}

	@Override
	public List<Map<String, String>> queryHzWaterAffairsInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectHzWaterAffairsInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryHzFyNaturalPerson(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectHzFyNaturalPerson", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjLoadBrief(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectGjjLoadBrief", appId);
	}

	@Override
	public List<Map<String, String>> queryHzVehicleInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectHzVehicleInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryHzVehiclePenaltyInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectHzVehiclePenaltyInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjxxInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectGjjxxInfo", appId);
	}
}
