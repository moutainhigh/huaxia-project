package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.report.VehicleInfoSearchResultDao;
import com.google.common.util.concurrent.AbstractService;
import com.huaxia.opas.service.report.VehicleInfoSearchResultService;

/**
 * 
 * @author dingguofeng
 * 车辆信息查询结果汇总报表
 *
 */

public class VehicleInfoSearchResultServiceImpl extends AbstractService
		implements VehicleInfoSearchResultService, Serializable {
	private static final long serialVersionUID = 3298185078995580514L;
	@Resource(name = "vehicleInfoSearchResultDao")
	private VehicleInfoSearchResultDao vehicleInfoSearchResultDao;
	public VehicleInfoSearchResultDao getVehicleInfoSearchResultDao(){
		return vehicleInfoSearchResultDao;
	}
	public void setVehicleInfoSearchResultDao(VehicleInfoSearchResultDao vehicleInfoSearchResultDao){
		this.vehicleInfoSearchResultDao = vehicleInfoSearchResultDao;
	}
	@Override
	protected void doStart() {
	}
	
	@Override
	protected void doStop() {
	}
	@Override
	public Map<String, Object> findVehicleInfoSearchResultDataByDate(Map<String, Object> paraMap) {
		Map<String, Object> paramsMap=  new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		List<Map<String, Object>> listMap = vehicleInfoSearchResultDao.selectVehicleInfoSearchResultDataByDate(paramsMap);
		//加工查询结果
		if(listMap.get(0).get("SELECTTOTALCOUNT").toString().equals("0")){
			listMap.get(0).put("SELECTSUCCANDOWNVEHICLE", "0");
			listMap.get(0).put("SELECTSUCCANDNOVEHICLE", "0");
			listMap.get(0).put("NAMEANDCARDISNOTMATCH", "0");
			listMap.get(0).put("SELECTFAIL", "0");
		}
		List<Map<String, Object>> listMapUse=new ArrayList<Map<String,Object>>();
		listMapUse.addAll(listMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", listMapUse);
		return dataMap;
	}


}
