package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.PoliceDao;

public class PoliceDaoImpl extends AbstractDAO implements PoliceDao {

	private static final long serialVersionUID = 3785274889666220889L;
	
	private static final String NAMESPACES = "Police.";

	@Override
	public Map<String, String> selectSummaryInfo(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfo", params);
	}

	@Override
	public Map<String, String> selectDetailInfo(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailInfo", params);
	}

	@Override
	public List<Object> selectDetailList(Map<String, String> params) {
		return getSqlMap().queryForList(NAMESPACES + "queryList", params);
	}

	@Override
	public Map<String, String> querytaskStatusInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querytaskStatusInfoByAppId", appId);
	}

	@Override
	public Map<String, String> queryPoliceInfo(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPoliceInfo", params);
	}
	
	@Override
	public Map<String, String> selectPoliceDetailInfo(Map<String, String> map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectPoliceDetailInfo", map);
	}

	@Override
	public int selectPoliceCountByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectPoliceCountByAppId", appId);
	}
	@Override
	public Map<String,String> selectPoliceStatusInfoByAppId(String appId){
		
		return getSqlMap().queryForObject(NAMESPACES + "queryPoliceStatusInfoByAppId", appId);
	}

	@Override
	public Map<String, String> selectTaxidInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTaxidInfoByAppId", appId);
	}

	@Override
	public Map<String, String> selectPoliceXpInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPoliceXpInfo", appId);
	}

	@Override
	public Map<String, String> selectpoliceSummaryInfo(Map<String, String> param) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPoliceSummaryInfo", param);
	}

	@Override
	public Map<String, String> selectPoliceXpDetail(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPoliceXpDetail", appId);
	}

	@Override
	public Map<String, String> querySummaryByAppId(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryByAppId", map);
	}

	@Override
	public Map<String, String>  selectC1Idtype(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryC1Idtype", appId);
	}

	@Override
	public Map<String, String> selectNciicForeignerInfo(Map<String, String> param) {
		return getSqlMap().queryForObject(NAMESPACES + "queryNciicForeignerInfo", param);
	}

	@Override
	public Map<String, String> selectC2Idtype(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryC2Idtype", appId);
	}

	@Override
	public Integer selectDependableIdentityCardByAppid(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectDependableIdentityCardByAppid", appId);
	}

	@Override
	public Map<String, String> selectSVoiceInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySVoiceInfoByAppId", appId);
	}

	@Override
	public String querySVoiceBackInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySVoiceBackInfoByAppId", appId);
	}

	@Override
	public Map<String, String> selectAuthResultByAppid(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectAuthResultByAppid", appId);
	}
	
}
