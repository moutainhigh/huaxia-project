package com.huaxia.opas.dao.credit.impl;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.credit.CreditCheckDao;
import com.huaxia.opas.domain.credit.Candidate;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.CreditSVoice;
import com.huaxia.opas.domain.credit.OpasTelcheckResult;
import com.huaxia.opas.domain.input.BizInpApplC1;
/*******************************************
 *@describe:征信提报、复核、调查、审批公用接口实现
 *@author：xiaoJianFeng
 *@date:2017-03-02
 *****************************************/
public class CreditCheckDaoImp extends AbstractDAO implements CreditCheckDao  {

	private static final long serialVersionUID = 5126362041387741671L;
	
	private static final String NAMESPACES = "credit.";
	
	@Override
	public List<CreditCheck> selectCreditCheckPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		List<CreditCheck> rows = getSqlMap().queryForList(NAMESPACES + "selectCreditCheckPage", paraMap, begNum,pageNum);
		return rows;	
	}
	
	@Override
	public String selectCreditCheckCount(Map<String, Object> paraMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditCheckCount", paraMap);
	}

	@Override
	public CreditCheck selectCreditCheckByApp_id(Map<String, String> paraMap) {
		  Object object=getSqlMap().queryForObject(NAMESPACES + "selectCreditCheckByApp_id", paraMap);
		return object!=null?(CreditCheck)object:null;
	}

	@Override
	public int updateCreditCheck(CreditCheck creditCheck) {
		return getSqlMap().update(NAMESPACES + "updateCreditCheck", creditCheck);
	}
	@Override
	public void insertCopyOpasAllotToAllotHis(CreditCheck creditCheck) {
		getSqlMap().insert(NAMESPACES+"insertCopyOpasAllotToAllotHis",creditCheck);
	}
	@Override
	public int updateCreditCheck1(CreditCheck creditCheck) {
		return getSqlMap().update(NAMESPACES + "updateCreditCheck1", creditCheck);
	}

	@Override
	public List<CreditCheck> selectCreditCheckCheatPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		List<CreditCheck> rows = getSqlMap().queryForList(NAMESPACES + "selectCreditCheckCheatPage", paraMap, begNum,pageNum);
		return rows;
	}

	@Override
	public String selectCreditCheckCheatCount(Map<String, Object> paraMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditCheckCheatCount", paraMap);
	}
	@Override
	public int insertReasonInfo(CreditCheck creditCheck) {
		return getSqlMap().insert(NAMESPACES + "insertReason", creditCheck);
	}
	@Override
	public List<CreditCheck> selectMentionAntiFraud(Map<String, String> paraMap) {
		return  getSqlMap().queryForList(NAMESPACES + "selectMentionAntiFraud", paraMap);
		
	}
	@Override
	public int insertOpasSubmitFraudInfo(CreditCheck creditCheck) {
		return getSqlMap().insert(NAMESPACES + "insertOpasSubmitFraudInfo", creditCheck);
	}
	@Override
	public List<CreditCheck> selectCreditCheatInvestigationPage(Map<String, Object> paraMap, int begNum,int pageNum) {
		List<CreditCheck> rows = getSqlMap().queryForList(NAMESPACES + "selectCreditCheatInvestigationPage", paraMap, begNum,pageNum);
		return rows;	
	}
	@Override
	public String selectCreditCheatInvestigationCount(Map<String, Object> paraMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditCheatInvestigationCount", paraMap);
	}
	@Override
	public List<CreditCheck> selectOpasApDict() {
		return getSqlMap().queryForList(NAMESPACES + "selectOpasApDict");
	}
	@Override
	public void updateCheatInvestigation(CreditCheck creditCheck) {
		 getSqlMap().update(NAMESPACES + "updateCheatInvestigation", creditCheck);
	}
	@Override
	public void updateOpasAllotApplyAllot(CreditCheck creditCheck) {
		 getSqlMap().update(NAMESPACES + "updateOpasAllotApplyAllot", creditCheck);
		
	}
	@Override
	public void deleteOpasReasonInfoByAppID(String app_id) {
		 getSqlMap().delete(NAMESPACES + "deleteOpasReasonInfoByAppID", app_id);
		
	}
	@Override
	public void deleteOpasSubmitfraudInfoByautoID(String autoId) {
		getSqlMap().delete(NAMESPACES + "deleteOpasSubmitfraudInfoByautoID", autoId);
		
	}
	@Override
	public List<CreditCheck> selectOpasOpasSubmitfraudInfoByautoID(String autoId) {
		return  getSqlMap().queryForList(NAMESPACES+"selectOpasOpasSubmitfraudInfoByautoID", autoId);
	}
	@Override
	public  List<CreditCheck> selectCreditCheatApprovalPage(CreditCheck creditCheck, int begNum, int pageNum) {
		List<CreditCheck> rows = getSqlMap().queryForList(NAMESPACES + "selectCreditCheatApprovalPage", creditCheck, begNum,pageNum);
		return rows;	
	}
	@Override
	public String selectCreditCheatApprovalCount(CreditCheck creditCheck) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditCheatApprovalCount", creditCheck);
	}
	 
	@Override
	public void insertOpasSubmitFraudInfoHis(CreditCheck creditCheck) {
		getSqlMap().insert(NAMESPACES + "insertOpasSubmitFraudInfoHis", creditCheck);
	}
	@Override
	public void copyOpasSubmitFraudInfoToHis(Map<String, String> paraMap) {
		getSqlMap().insert(NAMESPACES + "copyOpasSubmitFraudInfoToHis", paraMap);
	}
	@Override
	public List<CreditCheck> selectCandidate(Candidate candidate, int begNum, int pageNum) {
		List<CreditCheck> rows = getSqlMap().queryForList(NAMESPACES + "selectCandidate", candidate,begNum,pageNum);
		return rows;
	}
	@Override
	public String selectCandidateCount(Candidate candidate) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCandidateCount", candidate);
	}
	@Override
	public String selectOpasSubmitFraudInfoByOperationDate(Map<String, String> paraMap) {
		return getSqlMap().queryForObject(NAMESPACES+"selectOpasSubmitFraudInfoByOperationDate",paraMap);
	}
	@Override
	public void insertCopyOpasAllotApplyAllot(String app_id) {
		getSqlMap().insert(NAMESPACES+"copyOpasAllotApplyAllot",app_id);
	}
	@Override
	public List<CreditCheck> selectReasonByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"selectReasonByAppId",appId);
	}
	@Override
	public void updateReasonByAppId(CreditCheck creditCheck) {
		getSqlMap().update(NAMESPACES+"updateReasonByAppId",creditCheck);
	}
	@Override
	public void insertReasonList(List<CreditCheck> creditChecks) {
		getSqlMap().insert(NAMESPACES+"insertReasonList", creditChecks);
		
	}
	@Override
	public void deleteReasonById(String reasonID) {
		getSqlMap().delete(NAMESPACES+"deleteReasonById",reasonID);
		
	}
	@Override
	public void insertOpasTelcheckResult(OpasTelcheckResult telcheckResult) {
		getSqlMap().insert(NAMESPACES+"insertOpasTelcheckResult", telcheckResult);
	}
	@Override
	public int updateOne(Map<String, Object> map) throws CoreException {
		return getSqlMap().update(NAMESPACES+"updateOne",map);
	}
	@Override
	public int updateThree(Map<String, Object> map) throws CoreException {
		return getSqlMap().update(NAMESPACES+"updateThree",map);
	}
	@Override
	public BizInpApplC1 queryBizInpApplC1ByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryBizInpApplC1ByAppId", appId);
	}
	@Override
	public String queryGroup(String crediter) {
		return getSqlMap().queryForObject(NAMESPACES+"queryGroup",crediter);
	}
	@Override
	public int insertOpasAllotApplyAllotHis(CreditCheck creditCheck) {
		return getSqlMap().insert(NAMESPACES+"insertOpasAllotApplyAllotHis", creditCheck);
	}
	@Override
	public String querySubmitFraud(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"querySubmitFraud",appId);
	}
	@Override
	public int updateOpasAllotApplyAllot1(CreditCheck creditCheck) {
		return getSqlMap().update(NAMESPACES+"updateOpasAllotApplyAllot1",creditCheck);
	}
	@Override
	public String queryAllotApplyAllotByAppId(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"queryAllotApplyAllotByAppId",appId);
	}
	@Override
	public String selectOrgIdByUserId(String userId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryGroupId",userId);
	}
	@Override
	public List selectListApUserByOrgId(String orgId) {
		return getSqlMap().queryForList(NAMESPACES+"selectListApUserByOrgId",orgId);
	}
	@Override
	public List<Map<String, Object>> selectListApUserByListUserId(
			Map<String, Object> paramMaplistUserId) {

		return getSqlMap().queryForList(NAMESPACES+"selectListApUserByListUserId",paramMaplistUserId);
	}
	@Override
	public CreditCheck selfTheOne(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES+"selfTheOne", paramMap);
	}
	@Override
	public void updateOpasSubmitFraudInfo(CreditCheck creditCheck) {
		getSqlMap().update(NAMESPACES+"updateOpasSubmitFraudInfo", creditCheck);
	}
	@Override
	public List<CreditCheck> selectOpasOpasSubmitfraudInfoByAppID(String appId) {
		return  getSqlMap().queryForList(NAMESPACES+"selectOpasOpasSubmitfraudInfoByAppID", appId);
	}
	@Override
	public String queryqzCurrNodeByAppId(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"queryqzCurrNodeByAppId",appId);
	}
	@Override
	public String queryCurrNodeByAppId(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"queryCurrNodeByAppId",appId);
	}
	@Override
	public int countOpasSubmitfraudInfoByAppID(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"countOpasSubmitfraudInfoByAppID",appId);
	}
	@Override
	public String queryInvestMemo(String appId) {
		
		return getSqlMap().queryForObject(NAMESPACES+"queryInvestMemo",appId);
	}
	@Override
	public String selectWorkCountInvestigation(Map<String, String> paraMap) {
		return getSqlMap().queryForObject(NAMESPACES+"selectWorkCountInvestigation",paraMap);
	}
	@Override
	public void updateOpasAllotApplySubmitMemo(String appId) {
		getSqlMap().update(NAMESPACES+"updateOpasAllotApplySubmitMemo", appId);
	}
	@Override
	public String queryGroupName(String crediter) {
		return getSqlMap().queryForObject(NAMESPACES+"queryGroupName",crediter);
	}
	@Override
	public void updateOpasAllotApplyAllotToBack(CreditCheck creditCheck) {
		getSqlMap().update(NAMESPACES+"updateOpasAllotApplyAllotToBack", creditCheck);
	}
	@Override
	public String queryCurrOptUserByAppId(String check_number) {
		return getSqlMap().queryForObject(NAMESPACES+"queryCurrOptUserByAppId", check_number);
	}
	@Override
	public String queryqzVdelStatusByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryqzVdelStatusByAppId",appId);
	}
	@Override
	public String querySubmitTypeByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"querySubmitTypeByAppId",appId);
	}
	@Override
	public List queryReasonCodeByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"queryReasonCodeByAppId",appId);
	}
	@Override
	public String queryOrgNameByAppId(String orgNo) {
		return getSqlMap().queryForObject(NAMESPACES+"queryOrgNameByAppId",orgNo);
	}
	@Override
	public List<CreditCheck> selectysallotapplyallotID(String appId) {
		return  getSqlMap().queryForList(NAMESPACES+"selectysallotapplyallotID", appId);
	}
	@Override
	public void updateYsAllotApplyAllot(CreditCheck creditCheck) {
		getSqlMap().update(NAMESPACES+"updateYsAllotApplyAllot", creditCheck);
	}
	@Override
	public int insertysallotapplyallotID(CreditCheck creditCheck) {
		return getSqlMap().insert(NAMESPACES+"insertysallotapplyallotID", creditCheck);
	}
	@Override
	public List<Map<String, Object>> queryPfxcTime(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryPfxcTime",appId);
	}
	@Override
	public List<CreditSVoice> selectCreditSVoiceCheckPage(Map<String, Object> paraMap, int begNum, int pageNum) {
		List<CreditSVoice> rows = getSqlMap().queryForList(NAMESPACES + "selectCreditSVoiceCheckPage", paraMap, begNum,pageNum);
		return rows;
	}

	@Override
	public String selectCreditSVoiceCount(Map<String, Object> paraMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditSVoiceCount", paraMap);
	}

	@Override
	public Integer selectCallSVoiceCount(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCallSVoiceCount", appId);
	}
}
