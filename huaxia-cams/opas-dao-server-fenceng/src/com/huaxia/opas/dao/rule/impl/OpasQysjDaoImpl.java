package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasQysjDao;

public class OpasQysjDaoImpl   extends AbstractDAO implements OpasQysjDao,Serializable {

	private static final long serialVersionUID = 4279304041727557900L;


	@Override
	public List<Map<String, String>> querySzScoreInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.querySzScoreInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryXmInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.queryXmInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> querySzReportInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.querySzReportInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryHzInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.queryHzInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryXmGjjInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.queryXmGjjInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryWzInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.queryWzInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryYcInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.queryYcInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryNbInfoByAppId(String appId) {
		return getSqlMap().queryForList("OpasQysjInfo.queryNbInfoByAppId", appId);
	}
		
}
