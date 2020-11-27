package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.TxOperatorDao;

public class TxOperatorDaoImpl extends AbstractDAO implements TxOperatorDao {

	private static final long serialVersionUID = -3485921687599376511L;

	private static final String NAMESPACES = "TxOperator.";

	@Override
	public Map<String, String> selectTxOperSummaryInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectTxOperSummaryInfoByAppId", appId);
	}

	@Override
	public Map<String, String> selectUnicomAddressInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectUnicomAddressInfoByAppId", appId);
	}

	@Override
	public List<Map<String, String>> selectUnicomListMessage(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"selectUnicomListMessage", appId);
	}

	@Override
	public String querySfrzBaseData(Map<String, String> map) {
		return getSqlMap().queryForObject(NAMESPACES+"selectSfrzBaseData", map);
	}

	@Override
	public Map<String, String> queryNbBaseInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryNbBaseInfo", appId);
	}


}