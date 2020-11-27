package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.Opasbizinpapplc1Dao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public class Opasbizinpapplc1DaoImpl extends AbstractDAO implements Opasbizinpapplc1Dao ,Serializable{

	private static final long serialVersionUID = -5703201056975260619L;
	private static final Logger log = Logger.getLogger(Opasbizinpapplc1DaoImpl.class);
	private static final String NAMESPACES = "Opasbizinpapplc1.";
	private static final String NAMESPACES1 = "BizInpApplC1.";
	private static final String HAVECARDINFO_NAMESPACES = "OpasHaveCardCustInfoMapper.";// 已持卡客户map

	@Override
	public List<Opasbizinpapplc1> queryAppCount(Opasbizinpapplc1 on) {
		return getSqlMap().queryForList(NAMESPACES + "queryOpasbizinpapplc1Count", on);
	}

	@Override
	public List<Opasbizinpapplc1> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList(NAMESPACES + "selectByExample", example);
	}

	@Override
	public List<Opasbizinpapplc1> selectByPrimaryKey(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectByPrimaryKey", appId);
	}

	@Override
	public int selectSerialCount(Opasbizinpapplc1 on) {
		return getSqlMap().queryForObject(NAMESPACES + "selectSerialCount", on);
	}

	@Override
	public String querySampleNum() {
		return getSqlMap().queryForObject(NAMESPACES + "querySampleNum");
	}
	
	// 07/18 from wjf 
	@Override
	public List<Map<String,String>> queryFlag(String appId) {
		return  getSqlMap().queryForList(NAMESPACES + "queryFlag", appId);
	}

	// 07/18 from wjf
	@Override
	public List<Map<String, String>> selectMainCardInfoByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"selectCardInfoByAppId", appId);
	}
	
	@Override
	public int getPersonAge(String appId){
		 return getSqlMap().queryForObject(NAMESPACES1+"getPersonAge", appId);
	}
	
	@Override
	public String selectRelshipByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectRelshipByAppId", appId);
	}

	// 190806 from wjf 已持卡卡片账户状态为U时,查询账户额度
	@Override
	public List<Map<String, Object>> selectCreditLimitByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"selectCreditLimitByAppId", appId);
	}

	@Override
	public List<Map<String, String>> queryDefinitionParam() {
		return getSqlMap().queryForList(NAMESPACES+"queryDefinitionParam");
	}

	@Override
	public List<Map<String, String>> queryC2CardMsg(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryC2CardMsg", appId);
	}
	
}
