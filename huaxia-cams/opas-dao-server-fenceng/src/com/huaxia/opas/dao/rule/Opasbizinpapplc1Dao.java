package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.rule.Opasbizinpapplc1;

public interface Opasbizinpapplc1Dao {
	public List<Opasbizinpapplc1> queryAppCount (Opasbizinpapplc1 on);
	public List<Opasbizinpapplc1> selectByExample(Opasbizinpapplc1 example);
	public List<Opasbizinpapplc1> selectByPrimaryKey(String appId);
	public int selectSerialCount(Opasbizinpapplc1 on2);
	public String querySampleNum();
	public List<Map<String,String>> queryFlag(String appId);
	// 07/18 from wjf
	public List<Map<String, String>> selectMainCardInfoByAppId(String appId);
	int getPersonAge(String appId);
	public String selectRelshipByAppId(String appId);
	
	// 190806 from wjf 已持卡卡片账户状态为U时,查询账户额度
	public List<Map<String, Object>> selectCreditLimitByAppId(String appId);
	// 191113 from wjf
	public List<Map<String, String>> queryDefinitionParam();
	// 200805 from wjf 查询单办附卡证件类型
	public List<Map<String, String>> queryC2CardMsg(String appId);
	
}