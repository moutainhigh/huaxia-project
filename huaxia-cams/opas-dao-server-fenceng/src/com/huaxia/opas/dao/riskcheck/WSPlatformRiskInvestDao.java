package com.huaxia.opas.dao.riskcheck;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.WSPlatformRiskInvest;

public interface WSPlatformRiskInvestDao {
	int queryWSPlatformRiskInvestCount(Map<String, String> params);
	List<Map<String, String>> queryWSPlatformRiskInvestList(Map<String, String> params, int curNum, int pageNum);
	int queryWSPlatformRiskInvestCountNotParam(Map<String, String> params);
	List<Map<String, String>> queryWSPlatformRiskInvestListNotParam(Map<String, String> params, int curNum, int pageNum);
	int queryWSPlatformInvestResultCount(Map<String, String> params);
	List<Map<String, String>> queryWSPlatformInvestResultList(Map<String, String> params, int curNum, int pageNum);
	String checkRemarkInfoByAppId(Map<String, String> params);
	int updateBatch(WSPlatformRiskInvest wspri) throws CoreException;
	int updateBatchRefuse(WSPlatformRiskInvest wspri) throws CoreException;
	Map<String, String> queryRefuseParamByAppId(Map<String, Object> params);
	int insertBizInpApprovalForRefuse(Map<String, Object> paraMap) throws CoreException;
	int insertArchiveFlag(Map<String, Object> paraMap) throws CoreException;
	int updateContinueCirculateSubmit(WSPlatformRiskInvest wspri) throws CoreException;
	List<Map<String, Object>> queryInvokeBpmAppId(Map<String,Object> map);
	List<Map<String, String>> selectAppIds(List<String> appIdList);
	int insertTempBatchAppId(List<String> list) throws CoreException;
	List<Map<String, String>> selectTempAppIds();
	int updateTempAppIdFlag(WSPlatformRiskInvest wspri) throws CoreException;
	int deleteTempAppId(String appId);
	int autoDeleteTempAppIdByFlag();
}
