package com.huaxia.opas.dao.report.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.VehicleInfoSearchResultDetailDao;

public class VehicleInfoSearchResultDetailDaoImpl extends AbstractDAO implements VehicleInfoSearchResultDetailDao {

	private static final long serialVersionUID = 6752738792515353195L;
	private static final String NAMESPACES = "VehicleInfoSearchResultDetail.";
	@Override
	public List<Map<String, Object>> selectVehicleInfoSearchResultDetailDataByDate(Map<String, Object> paramsMap,int pageNum, int pageRows) {
		return getSqlMap().queryForList(NAMESPACES + "selectVehicleInfoSearchResultDetailDataByDate", paramsMap, pageNum,pageRows);
	}
	@Override
	public String getCountVehicleInfoSearchResultDetailDataByDate(Map<String, Object> paraMap) {
		return  this.getSqlMap().queryForObject(NAMESPACES + "getCountVehicleInfoSearchResultDetailDataByDate", paraMap);
	}
	@Override
	public String getC2CnameFromTableC2(Map<String, Object> appid) {
		return this.getSqlMap().queryForObject(NAMESPACES + "getC2CnameFromTableC2", appid);
	}

}
