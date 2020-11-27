package com.huaxia.opas.dao.report.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.QueryOperatorMsgDao;


public class QueryOperatorMsgDaoImpl extends AbstractDAO implements QueryOperatorMsgDao{
	private static final long serialVersionUID = -8058677710268061879L;
	private static final String NAMESPACES = "QueryOperatorMsg.";
	
	@Override
	public List<Map<String, Object>> queryOrgName() {
		List<Map<String, Object>> list = getSqlMap().queryForList(NAMESPACES + "queryOrgName");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(map!=null)
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> queryOperatorName(String orgId) {
		List<Map<String, Object>> list = getSqlMap().queryForList(NAMESPACES + "queryOperatorName",orgId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(map!=null)
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> queryApplyOrgName() {
		List<Map<String, Object>> list = getSqlMap().queryForList(NAMESPACES + "queryApplyOrgName");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(map!=null)
			resultList.add(map);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> queryApplyOperatorName(String orgName) {
		List<Map<String, Object>> list = getSqlMap().queryForList(NAMESPACES + "queryApplyOperatorName",orgName);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(map!=null)
			resultList.add(map);
		}
		return resultList;
	}
}
