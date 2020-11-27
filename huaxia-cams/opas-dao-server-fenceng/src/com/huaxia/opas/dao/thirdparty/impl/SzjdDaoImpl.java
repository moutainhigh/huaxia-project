package com.huaxia.opas.dao.thirdparty.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.SzjdDao;

public class SzjdDaoImpl extends AbstractDAO implements SzjdDao {

	private static final long serialVersionUID = -3485921687599376511L;

	private static final String NAMESPACES = "Szjd.";

	@Override
	public Map<String, String> selectSzjdInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectSzjdInfoByAppId", appId);
	}

}