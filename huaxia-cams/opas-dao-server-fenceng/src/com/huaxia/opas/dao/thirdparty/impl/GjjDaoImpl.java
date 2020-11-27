package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.GjjDao;

public class GjjDaoImpl extends AbstractDAO implements GjjDao {

	private static final long serialVersionUID = -4223432083508734555L;
	
	private static final String NAMESPACES = "GJJ.";
	
	@Override
	public Map<String, String> queryGjjInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryGjjInfo", appId);
	}
	
	@Override
	public Map<String, String> queryGjjBriefInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryGjjBriefInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjDetail(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryGjjDetail", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjBriefAnalysis(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryGjjBriefAnalysis", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjDetailAnalysis(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryGjjDetailAnalysis", appId);
	}

	@Override
	public List<Map<String, String>> queryCompanyAnalData(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryCompanyAnalData", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjLoadBrief(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryGjjLoadBrief", appId);
	}

	@Override
	public List<Map<String, String>> queryGjjLoadDetail(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryGjjLoadDetail", appId);
	}

	

}
