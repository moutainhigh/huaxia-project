package com.huaxia.opas.dao.crm.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.crm.CRMCommonDao;

public class CRMCommonDaoImpl extends AbstractDAO  implements CRMCommonDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4342811570541229379L;
	private static final String CRM_INFO = "CRM.";//CRM map
	@Override
	public Map<String, Object> queryCRMInfo(String appId) {
		return getSqlMap().queryForObject(CRM_INFO + "queryCustInfo", appId);
	}


	@Override
	public List<Object> queryWangyinInfo(String appId) {
		return getSqlMap().queryForList(CRM_INFO + "querywangyininfo", appId);
	}
	
	@Override
	public List<Object> queryJiejikaInfo(String appId) {
		return getSqlMap().queryForList(CRM_INFO + "queryjiejikainfo", appId);
	}


	@Override
	public List<Object> queryDanbaoInfo(String appId) {
		return getSqlMap().queryForList(CRM_INFO + "querydanbaoinfo", appId);
	}


	@Override
	public List<Object> queryDaikuanInfo(String appId) {
		return getSqlMap().queryForList(CRM_INFO + "querydaikuaninfo", appId);
	}

}
