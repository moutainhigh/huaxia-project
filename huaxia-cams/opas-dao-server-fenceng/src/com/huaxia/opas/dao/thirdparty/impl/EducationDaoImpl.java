package com.huaxia.opas.dao.thirdparty.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.EducationDao;

public class EducationDaoImpl extends AbstractDAO implements EducationDao {

	private static final long serialVersionUID = 5433287755578081130L;
	
	private static final String NAMESPACES = "Education.";

	@Override
	public Map<String, String> selectSummaryInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfo", appId);
	}

	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailInfo", appId);
	}

	@Override
	public String querySummaryInfoQuery(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoQuery", appId);
	}
}
