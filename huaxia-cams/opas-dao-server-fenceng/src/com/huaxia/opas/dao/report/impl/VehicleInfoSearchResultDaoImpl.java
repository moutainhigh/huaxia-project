package com.huaxia.opas.dao.report.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.VehicleInfoSearchResultDao;

public class VehicleInfoSearchResultDaoImpl extends AbstractDAO implements VehicleInfoSearchResultDao {
	private static final long serialVersionUID = 780525286575597609L;
	private static final String NAMESPACES = "VehicleInfoSearchResult.";
	@Override
	public List<Map<String, Object>> selectVehicleInfoSearchResultDataByDate(Map<String, Object> paramsMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectVehicleInfoSearchResultDataByDate", paramsMap);
	}

}
