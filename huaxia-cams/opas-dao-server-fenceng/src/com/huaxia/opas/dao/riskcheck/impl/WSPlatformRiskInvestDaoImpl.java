package com.huaxia.opas.dao.riskcheck.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.riskcheck.WSPlatformRiskInvestDao;
import com.huaxia.opas.domain.allot.TempAllot;
import com.huaxia.opas.domain.riskcheck.WSPlatformRiskInvest;

public class WSPlatformRiskInvestDaoImpl extends AbstractDAO implements WSPlatformRiskInvestDao{
	private static final long serialVersionUID = 3593252442210154058L;
	private static final String NAMESPACES = "OpasWSPlatformRiskInvest.";
	
	@Override
	public int queryWSPlatformRiskInvestCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWSPlatformRiskInvestCount", params);
	}

	@Override
	public List<Map<String, String>> queryWSPlatformRiskInvestList(Map<String, String> params, int curNum,
			int pageNum) {
		return  getSqlMap().queryForList(NAMESPACES + "queryWSPlatformRiskInvestList", params, curNum, pageNum);
	}
	
	@Override
	public int queryWSPlatformRiskInvestCountNotParam(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWSPlatformRiskInvestCountNotParam", params);
	}
	
	@Override
	public List<Map<String, String>> queryWSPlatformRiskInvestListNotParam(Map<String, String> params, int curNum,
			int pageNum) {
		return  getSqlMap().queryForList(NAMESPACES + "queryWSPlatformRiskInvestListNotParam", params, curNum, pageNum);
	}
	
	@Override
	public int queryWSPlatformInvestResultCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWSPlatformInvestResultCount", params);
	}
	
	@Override
	public List<Map<String, String>> queryWSPlatformInvestResultList(Map<String, String> params, int curNum,
			int pageNum) {
		return  getSqlMap().queryForList(NAMESPACES + "queryWSPlatformInvestResultList", params, curNum, pageNum);
	}
	
	@Override
	public String checkRemarkInfoByAppId(Map<String, String> params){
		return  getSqlMap().queryForObject(NAMESPACES + "checkRemarkInfoByAppId", params);
	}

	@Override
	public int updateBatch(WSPlatformRiskInvest wspri) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatch", wspri);
	}
	
	@Override
	public int updateBatchRefuse(WSPlatformRiskInvest wspri) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBatchRefuse", wspri);
	}
	
	@Override
	public Map<String, String> queryRefuseParamByAppId(Map<String, Object> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryRefuseParamByAppId", params);
	}

	@Override
	public int insertBizInpApprovalForRefuse(Map<String, Object> paraMap) throws CoreException {
		return  getSqlMap().insert(NAMESPACES + "insertBizInpApprovalForRefuse", paraMap);
	}
	
	@Override
	public int insertArchiveFlag(Map<String, Object> paraMap) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertArchiveFlag", paraMap);
	}
	
	@Override
	public int updateContinueCirculateSubmit(WSPlatformRiskInvest wspri) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateContinueCirculateSubmit", wspri);
	}

	@Override
	public List<Map<String, Object>> queryInvokeBpmAppId(Map<String,Object> paramsMap){
		return  getSqlMap().queryForList(NAMESPACES + "queryInvokeBpmAppId", paramsMap);
	}
	
	@Override
	public List<Map<String, String>> selectAppIds(List<String> appIdList) {
		return getSqlMap().queryForList(NAMESPACES + "selectAppIds", appIdList);
	}
	
	@Override
	public int insertTempBatchAppId(List<String> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTempBatchAppId", list);
	}
	
	@Override
	public List<Map<String, String>> selectTempAppIds() {
		return getSqlMap().queryForList(NAMESPACES + "selectTempAppIds");
	}
	
	@Override
	public int updateTempAppIdFlag(WSPlatformRiskInvest wspri) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTempAppIdFlag", wspri);
	}
	
	@Override
	public int deleteTempAppId(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteTempAppId", appId);
	}
	
	@Override
	public int autoDeleteTempAppIdByFlag() {
		return getSqlMap().delete(NAMESPACES + "autoDeleteTempAppIdByFlag");
	}
}
