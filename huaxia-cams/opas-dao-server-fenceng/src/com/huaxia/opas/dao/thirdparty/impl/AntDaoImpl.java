package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.AntDao;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.rule.Opasbizzmivsdata;

public class AntDaoImpl extends AbstractDAO implements AntDao {

	private static final long serialVersionUID = -6602694060952015107L;

	private static final String NAMESPACES = "Ant.";

	@Override
	public Map<String, String> selectSummaryInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoByAppId", appId);
	}

	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailInfoByAppId", appId);
	}
	
	@Override
	public Map<String,String> selectDetailAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailAppId", appId);
	}
	
	@Override
	public Opasbizzmivsdata selectByPrimaryKey(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", appId);
	}

	@Override
	public List<Map<String, Object>> selectZMRiskTypesGroup(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectZMRiskTypesGroup", paramMap);
	}

	@Override
	public int selectZMriskListMessageCount(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectZMriskListMessageCount", paramMap);
	}

	@Override
	public List<Map<String, String>> selectZMriskListMessageData(Map map) {
		return getSqlMap().queryForList(NAMESPACES + "selectZMriskListMessageData", map);
	}

	@Override
	public Map<String, String> selectThreePartiesTaskRequestInfo(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectThreePartiesTaskRequestInfo", map);
	}
	@Override
	public List<Map<String, Object>> selectAntRiskOrder(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectAntRiskOrder", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectBrRistOrder1(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBrRistOrder1", paramMap);
	}
	@Override
	public List<Map<String, Object>> selectBrRistOrder2(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBrRistOrder2", paramMap);
	}
	@Override
	public String selectCustRiskLevel(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCustRiskLevel", paramMap);
	}

	@Override
	public String selectIvsScoreByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectIvsScoreByAppId", appId);
	}
}
