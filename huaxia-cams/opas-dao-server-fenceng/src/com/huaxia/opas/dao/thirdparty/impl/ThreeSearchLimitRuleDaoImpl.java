package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.ThreeSearchLimitRuleDao;

public class ThreeSearchLimitRuleDaoImpl extends AbstractDAO implements ThreeSearchLimitRuleDao {
	
	private static final long serialVersionUID = -7379523005319271001L;
	private static final String NAMESPACES = "ThreeSearchLimitRule.";
	@Override
	public void saveAddRule(Map<String, Object> paramMap) {
		 getSqlMap().insert(NAMESPACES + "saveAddRule", paramMap);
	}
	@Override
	public int selectCountByTypeUniqueCode(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountByTypeUniqueCode", queryParamMap);
	}
	@Override
	public List<Map<String, Object>> selectExistedRuleByType(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectExistedRuleByType", queryParamMap);
	}
	@Override
	public void deleteRulesByIdList(Map<String, Object> deleteParamMap) {
		getSqlMap().delete(NAMESPACES + "deleteRulesByIdList", deleteParamMap);
	}
	@Override
	public void saveSearchNumControl(Map<String, Object> saveOrUpdateParamMap) {
		getSqlMap().insert(NAMESPACES + "saveSearchNumControl", saveOrUpdateParamMap);
	}
	@Override
	public int selectCountSearchNumLimitByType(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountSearchNumLimitByType", queryParamMap);
	}
	@Override
	public void updateSearchNumControl(Map<String, Object> saveOrUpdateParamMap) {
		 getSqlMap().update(NAMESPACES + "updateSearchNumControl", saveOrUpdateParamMap);
	}
	@Override
	public Map<String, Object> selectObjectSearchNumLimitByType(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectObjectSearchNumLimitByType", queryParamMap);
	}
	@Override
	public int selectCountVehicleTimeQuantumSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountVehicleTimeQuantumSucceed", queryParamMap);
	}
	@Override
	public int selectCountTypeLimitSearch(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountTypeLimitSearch", queryParamMap);
	}
	@Override
	public void updateTypeLimitSearch(Map<String, Object> saveOrUpdatequeryParamMap) {
		 getSqlMap().update(NAMESPACES + "updateTypeLimitSearch", saveOrUpdatequeryParamMap);
	}
	@Override
	public void saveTypeLimitSearch(Map<String, Object> saveOrUpdatequeryParamMap) {
		 getSqlMap().insert(NAMESPACES + "saveTypeLimitSearch", saveOrUpdatequeryParamMap);
	}
	@Override
	public List<Map<String, Object>> selectTypeLimitSearch(Map<String, Object> queryParamMap) {
		return  getSqlMap().queryForList(NAMESPACES + "selectTypeLimitSearch", queryParamMap);
	}
	@Override
	public Map<String, Object> selectVerhicleCheckInfo(Map<String, Object> queryParamMap) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectVerhicleCheckInfo", queryParamMap);
	}	
	@Override
	public Map<String, Object> selectBxVerhicleCheckInfo(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectBxVerhicleCheckInfo", appId);
	}
	@Override
	public int selectCountFicoTimeQuantumSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountFicoTimeQuantumSucceed", queryParamMap);
	}
	@Override
	public int selectCountTyTimeQuantumSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountTyTimeQuantumSucceed", queryParamMap);
	}
	@Override
	public int selectCountSjTimeQuantumSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountSjTimeQuantumSucceed", queryParamMap);
	}
	@Override
	public int selectCountQyTimeQuantumSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountQyTimeQuantumSucceed", queryParamMap);
	}
	@Override
	public int selectCountTxyyTimeQuantumSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountTxyyTimeQuantumSucceed", queryParamMap);
	}
	/*@Override
	public int selectLongLoanCountSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectLongLoanCountSucceed", queryParamMap);
	}*/
	@Override
	public Map<String, String> selectLoanNumber(String yearNumber){
		return getSqlMap().queryForObject(NAMESPACES + "selectLoanNumber", yearNumber);
		
	}
	@Override
	public Map<String, String> selectSumLoanNumber(String yearNumber){
		return getSqlMap().queryForObject(NAMESPACES + "selectSumLoanNumber", yearNumber);
		
	}
	@Override
	public int selectRealLoanCount(Map<String, Object> queryParamMap){
		return getSqlMap().queryForObject(NAMESPACES + "selectRealLoanCount", queryParamMap);
		
	}
	@Override
	public int selectLongLoanCountSucceed(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectLongLoanCountSucceed", queryParamMap);
	}
	@Override
	public int selectNetDtjdCount(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectNetDtjdCount", queryParamMap);
	}
	@Override
	public Map<String, String> queryLoanNumberNet(String yearNumber) {
		return getSqlMap().queryForObject(NAMESPACES + "queryLoanNumberNet", yearNumber);
	}
	
	@Override
	public List<Map<String, Object>> selectBaoXinRuleByType(Map<String, Object> queryMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBaoXinRuleByType", queryMap);
	}
	@Override
	public void deleteBaoXinRulesByIdList(Map<String, Object> deleteParamMap) {
		getSqlMap().delete(NAMESPACES + "deleteBaoXinRulesByIdList", deleteParamMap);	
	}
	@Override
	public int selectBaoXinCount(Map<String, Object> queryMapTwo) {
		return getSqlMap().queryForObject(NAMESPACES + "selectBaoXinCount", queryMapTwo);
	}

	@Override
	public Map<String, String> queryBaoXinNumberNet(String yearNumber) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBaoXinNumberNet", yearNumber);
	}
	@Override
	public List<Map<String, Object>> selectPadPortraitComparisonRuleByType(Map<String, Object> queryParamMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectPadPortraitComparisonRuleByType", queryParamMap);
	}
	@Override
	public int selectCountPadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountPadPortraitComparisonTypeLimitSearch", initialMap);
	}
	@Override
	public void updatePadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap) {
		 getSqlMap().update(NAMESPACES + "updatePadPortraitComparisonTypeLimitSearch", initialMap);
		
	}
	@Override
	public void savePadPortraitComparisonTypeLimitSearch(Map<String, Object> initialMap) {
		 getSqlMap().insert(NAMESPACES + "savePadPortraitComparisonTypeLimitSearch", initialMap);
		
	}
	@Override
	public void deletePadPortraitComparisonRulesByIdList(Map<String, Object> deleteParamMap) {
		getSqlMap().delete(NAMESPACES + "deletePadPortraitComparisonRulesByIdList", deleteParamMap);
		
	}
	@Override
	public List<String> queryAllTypeLimitSearch() {
		return getSqlMap().queryForList(NAMESPACES + "selectAllTypeLimitSearch");
	}

}
