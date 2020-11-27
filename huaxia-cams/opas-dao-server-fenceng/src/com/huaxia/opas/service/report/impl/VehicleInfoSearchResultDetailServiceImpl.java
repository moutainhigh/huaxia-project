package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.util.concurrent.AbstractService;
import com.huaxia.opas.dao.report.VehicleInfoSearchResultDetailDao;
import com.huaxia.opas.service.report.VehicleInfoSearchResultDetailService;

public class VehicleInfoSearchResultDetailServiceImpl extends AbstractService
		implements VehicleInfoSearchResultDetailService, Serializable {

	private static final long serialVersionUID = 3607863111507853643L;
	@Resource(name = "vehicleInfoSearchResultDetailDao")
	private VehicleInfoSearchResultDetailDao vehicleInfoSearchResultDetailDao;
	public VehicleInfoSearchResultDetailDao getVehicleInfoSearchResultDetailDao(){
		return vehicleInfoSearchResultDetailDao;
	}
	public void setVehicleInfoSearchResultDetailDao(VehicleInfoSearchResultDetailDao vehicleInfoSearchResultDetailDao){
		this.vehicleInfoSearchResultDetailDao = vehicleInfoSearchResultDetailDao;
	}

	@Override
	protected void doStart() {
	}

	@Override
	protected void doStop() {
	}

	@Override
	public Map<String, Object> findVehicleInfoSearchResultDetailDataByDate(Map<String, Object> paraMap, int pageNum, int pageRows) {
		Map<String, Object> paramsMap=  new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		paramsMap.put("choseClass", paraMap.get("choseClass"));
		List<Map<String, Object>> listMap = vehicleInfoSearchResultDetailDao.selectVehicleInfoSearchResultDetailDataByDate(paramsMap,pageNum,pageRows);
		if(listMap != null){
			for(Map<String, Object> map : listMap){
				if(map.get("QUERY_RESULT") != null && map.get("QUERY_RESULT").toString().equals("1") && map.get("STATUS") != null && map.get("STATUS").toString().equals("1")){
					map.put("RESULT", "查询成功且“有车”");
				}else if(map.get("QUERY_RESULT") != null && map.get("QUERY_RESULT").toString().equals("1") && map.get("STATUS") != null && map.get("STATUS").toString().equals("0")){
					map.put("RESULT", "查询成功且“无车”");
				}else if(map.get("QUERY_RESULT") != null && map.get("QUERY_RESULT").toString().equals("-1")){
					map.put("RESULT", "查询失败");
				}else if(map.get("ERROR_CODE").toString().equals("40100")){
					map.put("RESULT", "姓名和身份证号不匹配");
				}else{
					map.put("RESULT", "查询失败");
				}
				map.remove("QUERY_RESULT");
				map.remove("STATUS");
				
				//如果单独申请的附卡,那么C1_CNAME需要在c2表里查询
				if(map.get("C1C2_FLAG") != null && map.get("C1C2_FLAG").toString().equals("2")){
					paramsMap.put("APP_ID", map.get("APP_ID"));
					String C2_CNAME = vehicleInfoSearchResultDetailDao.getC2CnameFromTableC2(paramsMap);
					map.put("C1_CNAME", C2_CNAME);//将C2表的C2_CNAME赋值到查询结果中.
				}
				map.remove("C1C2_FLAG");
				
			}
		}
		List<Map<String, Object>> listMapUse=new ArrayList<Map<String,Object>>();
		listMapUse.addAll(listMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int total =0;
		String totalNum=vehicleInfoSearchResultDetailDao.getCountVehicleInfoSearchResultDetailDataByDate(paramsMap);
		if(totalNum!=null){
			total=Integer.parseInt(totalNum);
		}
		dataMap.put("rows", listMapUse);
		dataMap.put("total", total);
		return dataMap;
	}
}
