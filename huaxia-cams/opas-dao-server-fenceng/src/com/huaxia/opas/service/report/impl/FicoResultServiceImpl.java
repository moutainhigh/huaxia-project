package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.util.concurrent.AbstractService;
import com.huaxia.opas.dao.report.FicoResultDao;
import com.huaxia.opas.service.report.FicoResultService;

public class FicoResultServiceImpl extends AbstractService implements FicoResultService, Serializable {

	private static final long serialVersionUID = 2224714627246525378L;
	@Resource(name = "ficoResultDao")
	private FicoResultDao ficoResultDao;
	public FicoResultDao getFicoDatailResultDao(){
		return ficoResultDao;
	}
	public void setFicoDatailResultDao(FicoResultDao ficoResultDao){
		this.ficoResultDao = ficoResultDao;
	}

	@Override
	public Map<String, Object> queryFicoSummaryDataByDate(Map<String, Object> paraMap) {
		Map<String, Object> paramsMap=  new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		List<Map<String, Object>> listMap = ficoResultDao.selectFicoSummaryResultDataByDate(paramsMap);
		if(listMap.get(0).get("SELECTTOTALCOUNT").toString().equals("0")){
			listMap.get(0).put("SELECTSUCESS", "0");
			listMap.get(0).put("SELECTBAD", "0");
		}
		List<Map<String, Object>> listMapUse=new ArrayList<Map<String,Object>>();
		listMapUse.addAll(listMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", listMapUse);
		return dataMap;
	}

	@Override
	public Map<String, Object> queryFicoDetailDataByDate(Map<String, Object> paraMap, int pageNum,
			int pageRows) {
		Map<String, Object> paramsMap=  new HashMap<String, Object>();
		paramsMap.put("beginDate",paraMap.get("beginDate"));
		paramsMap.put("endDate",paraMap.get("endDate"));
		paramsMap.put("choseClass", paraMap.get("choseClass"));
		List<Map<String, Object>> listMap = ficoResultDao.selectFicoDetailDataByDate(paramsMap,pageNum,pageRows);
		//加工返回的数据  {RESULT=SELECTSUCCESS, APP_ID=180127BE76PBN999}
		if(listMap != null){
			for(Map<String, Object> map : listMap){
				if(map.get("RET_CODE") != null && map.get("RET_CODE").toString().equals("000")){
					map.put("RESULT", "查询成功");
				}else{
					map.put("RESULT", "查询失败");
				}				
			}
		}
		List<Map<String, Object>> listMapUse=new ArrayList<Map<String,Object>>();
		listMapUse.addAll(listMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int total =0;
		String totalNum=ficoResultDao.getCountFicoDetailDataByDate(paramsMap);
		if(totalNum!=null){
			total=Integer.parseInt(totalNum);
		}
		dataMap.put("rows", listMapUse);
		dataMap.put("total", total);
		return dataMap;
	}
	

	
	@Override
	protected void doStart() {		
	}

	@Override
	protected void doStop() {
	}
	
}
