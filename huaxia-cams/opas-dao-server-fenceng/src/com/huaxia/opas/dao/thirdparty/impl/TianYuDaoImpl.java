package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.TianYuDao;

public class TianYuDaoImpl extends AbstractDAO implements TianYuDao {

	private static final long serialVersionUID = -3485921687599376511L;

	private static final String NAMESPACES = "TianYu.";

	@Override
	public Map<String, String> selectTianYuScoreInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectTianYuScoreInfoByAppId", appId);
	}
	@Override
	public List<Map<String, String>> selectTianYuRiskCodeByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"selectTianYuRiskCodeByAppId", appId);
	}

}