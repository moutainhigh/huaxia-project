package com.huaxia.opas.dao.thirdparty.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.PYPCRDao;

public class PYPCRDaoImpl extends AbstractDAO implements PYPCRDao {
	private static final long serialVersionUID = 4437516530459456818L;
	
	private static final String NAMESPACES = "PYPCR.";
	
	@Override
	public Map<String, Object> selectPYPCRBaseInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectPYPCRBaseInfo", appId);
	}

	@Override
	public Map<String, Object> selectPYPCRInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectPYPCRInfo", appId);
	}

	@Override
	public Map<String, Object> selectPYPCR60Info(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectPYPCR60Info", appId);
	}

	@Override
	public Map<String, Object> selectPYPCR60SyllogeInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectPYPCR60SyllogeInfo", appId);
	}

}
