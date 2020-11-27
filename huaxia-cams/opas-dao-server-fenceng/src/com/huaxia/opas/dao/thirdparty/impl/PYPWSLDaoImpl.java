package com.huaxia.opas.dao.thirdparty.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.PYPWSLDao;

public class PYPWSLDaoImpl extends AbstractDAO implements PYPWSLDao{
	private static final long serialVersionUID = 7506144288706516395L;
	
	private static final String NAMESPACES = "PYPWSL.";

	@Override
	public Map<String, Object> selectAllPYPWSLInfo(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"selectAllPYPWSLInfo", appId);
	}

}
