package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.Credit100Dao;

public class Credit100DaoImpl extends AbstractDAO implements Credit100Dao {
	
	private static final long serialVersionUID = 2128890141066583741L;
	
	private static final String NAMESPACES = "Credit100.";

	@Override
	public Map<String, String> selectSummaryInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfo", appId);
	}

	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailInfo", appId);
	}
	
	@Override
	public Map<String,String> selectDetailAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailAppId", appId);
	}

	@Override
	public Map<String, String> Query_biz_speciallist_cell_data(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "Query_biz_speciallist_cell_data", appId);
	}

	@Override
	public Map<String, String> Query_OPAS_BIZ_SPECIALLIST_LM_DATA(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "Query_OPAS_BIZ_SPECIALLIST_LM_DATA", appId);
	}

	@Override
	public Map<String, String> Query_OPAS_BIZ_CONSUMPTION_DATA(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "Query_OPAS_BIZ_CONSUMPTION_DATA", appId);
	}

	@Override
	public List<String> Query_task_request_info(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "Query_task_request_info", appId);
	}

	// add by qingfeng.xiu 20190218 17:40
	@Override
	public int Query_bairong_query_result(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "Query_bairong_query_result", appId);
	}

}
