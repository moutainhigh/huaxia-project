package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.report.QueryOperatorMsgDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.report.QueryOperatorMsgService;

public class QueryOperatorMsgServiceImpl extends AbstractService implements QueryOperatorMsgService, Serializable{
	private static final long serialVersionUID = -2094526262335574884L;
	@Resource(name = "queryOperatorMsgDao")
	private QueryOperatorMsgDao queryOperatorMsgDao;

	public QueryOperatorMsgDao getQueryOperatorMsgDao() {
		return queryOperatorMsgDao;
	}
	public void setQueryOperatorMsgDao(QueryOperatorMsgDao queryOperatorMsgDao) {
		this.queryOperatorMsgDao = queryOperatorMsgDao;
	}
	@Override
	public List<Map<String, Object>> queryOrgName() throws CoreException {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> cardMap = new HashMap<String, Object>();
		cardMap.put("orgNo", "");
		cardMap.put("orgName", "--请选择--");
		listMap.add(cardMap);
		List<Map<String, Object>> resultList = queryOperatorMsgDao.queryOrgName();
		if(!resultList.isEmpty()){
			listMap.addAll(resultList);
		}
		return listMap;
	}
	@Override
	public List<Map<String, Object>> queryOperatorName(String orgId) {
		List<Map<String, Object>> resultList = queryOperatorMsgDao.queryOperatorName(orgId);
		return resultList;
	}
	@Override
	public List<Map<String, Object>> queryApplyOrgName() {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> cardMap = new HashMap<String, Object>();
		cardMap.put("orgNo", "");
		cardMap.put("orgName", "--请选择--");
		listMap.add(cardMap);
		List<Map<String, Object>> resultList = queryOperatorMsgDao.queryApplyOrgName();
		if(!resultList.isEmpty()){
			listMap.addAll(resultList);
		}
		return listMap;
	}
	@Override
	public List<Map<String, Object>> queryApplyOperatorName(String orgName) {
		List<Map<String, Object>> resultList = queryOperatorMsgDao.queryApplyOperatorName(orgName);
		return resultList;
	}
	

}
