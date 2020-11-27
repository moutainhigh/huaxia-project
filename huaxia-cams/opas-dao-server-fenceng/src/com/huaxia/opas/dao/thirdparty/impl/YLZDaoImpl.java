package com.huaxia.opas.dao.thirdparty.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.YLZDao;

public class YLZDaoImpl extends AbstractDAO implements YLZDao {
	private static final long serialVersionUID = -2784879810401935715L;
	
	private static final String NAMESPACES = "YLZ.";
	@Override
	public Map<String, String> selectAllYLZInfo(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"selectAllYLZInfo", appId);
	}
	@Override
	public int selectCount(String appId) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "selectCount", appId)));
	}
	@Override
	public Map<String, String> queryHzGjjxxInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryHzGjjxxInfo", appId);
	}
	@Override
	public Map<String, String> queryXMPersonal(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryXMPersonal", appId);
	}
	@Override
	public Map<String, String> queryXmGjjInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryXmGjjInfo", appId);
	}
	@Override
	public Map<String, String> queryWzgjjInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryWzgjjInfo", appId);
	}
	@Override
	public Map<String, String> queryYcInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryYcInfo", appId);
	}

}
