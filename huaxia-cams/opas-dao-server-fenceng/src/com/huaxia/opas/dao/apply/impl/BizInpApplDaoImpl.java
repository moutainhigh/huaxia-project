package com.huaxia.opas.dao.apply.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.apply.BizInpApplDao;
import com.huaxia.opas.domain.input.BizInpAppl;
/**
 * 进件信息
 * @author xiebinxu
 * 2017年2月25日
 */
public class BizInpApplDaoImpl extends AbstractDAO implements BizInpApplDao{

	private static final long serialVersionUID = -8888388888357864632L;

	private static final String NAMESPACES = "BizInpAppl.";

	@Override
	public int queryCount(Map record) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCount", record);
	}

	@Override
	public List queryList(Map record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplList", record, curNum, pageNum);
	}
	
	@Override
	public List queryBizInpApplListForExamine(Map record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplListForExamine", record, curNum, pageNum);
	}
	
	@Override
	public int queryCountSp(Map record) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplSPCount", record);
	}

	@Override
	public List queryListSp(Map record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplSPList", record, curNum, pageNum);
	}
	
	@Override
	public int queryCountWc(Map record) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCountWc", record);
	}
	@Override
	public int queryGtCount(Map record) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryGtCount", record);
	}
	@Override
	public List queryGtList(Map record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryGtList", record, curNum, pageNum);
	}
	@Override
	public List queryListWc(Map record, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplListWc", record, curNum, pageNum);
	}
	@Override
	public List selectAppList(BizInpAppl biz, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAppList", biz, curNum, pageNum);
	}
	
	@Override
	public int selectAppCount(BizInpAppl biz) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectAppCount", biz);
	}
	
	/**
	 * 工作量统计方法
	 * susha  2017/03/26
	 */
	//audit工作量统计之授信审批完成量
	@Override
	public int queryFinishedCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryFinishedCount", map);
	}
	//audit工作量统计之提至高级审批量
	@Override
	public int queryToHighlevelCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryToHighlevelCount", map);
	}
	//audit工作量统计之完成归档量
	@Override
	public int queryToFileCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryToFileCount", map);
	}
	//examine工作量统计之审查完成量
	@Override
	public int queryFinishedCount1(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryFinishedCount1", map);
	}
	//examine工作量统计之审查未完成量
	@Override
	public int queryUnfinishedCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryUnfinishedCount", map);
	}

	@Override
	public Map<String, Object> selectProcessIdByAppId(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectProcessIdByAppId", paramMap);
	}
	
	@Override
	public int queryBizInpApplCountStatusZero(String userCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCountStatusZero", userCode);
	}
	
	@Override
	public int queryBizInpApplCountStatusTwo(String userCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCountStatusTwo", userCode);
	}

	@Override
	public int queryBizInpApplCountStatusThree(String userCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCountStatusThree", userCode);
	}

	@Override
	public int queryBizInpApplCountStatusFour(String userCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCountStatusFour", userCode);
	}
	@Override
	public List<BizInpAppl> queryBizInpApplByCurrOptUser(String currOptUser) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplByCurrOptUser", currOptUser);
	}
	@Override
	public int queryCountStatusAndUserCodeAnddelStatus(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountStatusAndUserCodeAnddelStatus", map);
	}
	
	@Override
	public int queryFinisedApp(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryFinisedApp", map);
	}
	
	@Override
	public List<BizInpAppl> queryBizInpApplByCurrOptUserCheckup(String userCode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplByCurrOptUserCheckup", userCode);
	}
	
	@Override
	public List<BizInpAppl> queryBizInpApplByCurrOptUserApprove(String userCode) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryBizInpApplByCurrOptUserApprove", userCode);
	}

	@Override
	public int selectBizInpApplZxListFinishedCount(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectBizInpApplZxListFinishedCount", paramMap);
	}

	@Override
	public List selectBizInpApplZxListFinishedList(Map paramMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplZxListFinishedList", paramMap, curNum, pageNum);
	}

	@Override
	public Map selectAllotApplyCurrStatusByMap(Map paramMap) {
		
		return getSqlMap().queryForObject(NAMESPACES + "selectAllotApplyCurrStatusByMap", paramMap);
	}

	@Override
	public Map selectAllotApplyCurrStatusByGroupMap(Map paramMap) {
		
		return getSqlMap().queryForObject(NAMESPACES + "selectAllotApplyCurrStatusByGroupMap", paramMap);
	}
	
	@Override
	public void insertCopyOpasAllotToAllotHis(Map paramMap) {
		getSqlMap().insert(NAMESPACES+"insertCopyOpasAllotToAllotHis",paramMap);
	}

	@Override
	public Map selectCreditDecisionResultByAppId(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCreditDecisionResultByAppId", paramMap);
	}

	@Override
	public Map selectUserOrgOrgIdByUserUuid(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectUserOrgOrgIdByUserUuid", paramMap);
	}

	@Override
	public Map selectApOrgOrgNoByOrgId(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectApOrgOrgNoByOrgId", paramMap);
	}

	@Override
	public void updateAllotApplyAllotByAppId(Map paramMap) {
		getSqlMap().update(NAMESPACES+"updateAllotApplyAllotByAppId",paramMap);
	}

	@Override
	public List<Map> selectListAllotApplyZxRobByMap(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectListAllotApplyZxRobByMap",paramMap);
	}
	@Override
	public int selectBizInpApplZxListHangUpCount(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectBizInpApplZxListHangUpCount", paramMap);
	}

	@Override
	public List selectBizInpApplZxListHangUpList(Map record, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplZxListHangUpList", record, curNum, pageNum);
	}

	@Override
	public List<Map<String,Object>> selectListApUserByListUserId(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectListApUserByListUserId",paramMap);
	}

	@Override
	public void updateZxApplyYdjHangUpByAppIds(Map paramMap) {
		getSqlMap().update(NAMESPACES+"updateZxApplyYdjHangUpByAppIds",paramMap);
	}


	@Override
	public int queryYDJFinishedCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES+"queryYDJFinishedCount",map);
	}

	@Override
	public int queryYDJUnfinishedCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES+"queryYDJUnfinishedCount",map);
	}

	@Override
	public int queryYDJSub2Other(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES+"queryYDJSub2Other",map);
	}


	@Override
	public void updateZxApplyFinishedTakeBackByListAppId(Map paramMap) {
		getSqlMap().update(NAMESPACES+"updateZxApplyFinishedTakeBackByListAppId",paramMap);
	}

	@Override
	public int updateAllotByMapLikeAppId(Map<String, Object> paramMap) {
	  return  getSqlMap().update(NAMESPACES+"updateAllotByMapLikeAppId",paramMap);
	}

	@Override
	public void insertCopyOpasAllotToAllotHisByListAppId(Map paramMap) {
		getSqlMap().insert(NAMESPACES+"insertCopyOpasAllotToAllotHisByListAppId",paramMap);
	}

	@Override
	public List<Map> selectAllotApplyByMapLikeAppId(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotApplyByMapLikeAppId", paramMap);
	}

	@Override
	public int selectZxIndividualQueueDataCount(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectZxIndividualQueueDataCount", paramMap);
	}

	@Override
	public List selectZxIndividualQueueDataList(Map paramMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectZxIndividualQueueDataList", paramMap, curNum, pageNum);
	}

	@Override
	public int queryCountForExamine(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountForExamine", map);
	}

	@Override
	public int queryBizInpApplCountStatusFinised(String userCode) throws CoreException {
		
		return getSqlMap().queryForObject(NAMESPACES + "queryBizInpApplCountStatusFinised", userCode);
	}

	@Override
	public List<Map> selectAllotApplyByMapLikeCurrstatusAppId(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotApplyByMapLikeCurrstatusAppId", paramMap);
	}

	@Override
	public List<Map<String, Object>> selectAllotApplyByMapLikeListAppId(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectAllotApplyByMapLikeListAppId",paramMap);
	}

	@Override
	public Map<String, Object> selectApUserByUuIdUserCode(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectApUserByUuIdUserCode",paramMap);
	}

	@Override
	public List<BizInpAppl> selectBizInpApplByUserCode(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplByUserCode",paramMap);
	}

	@Override
	public List<BizInpAppl> selectBizInpApplByUserCodeApprove(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplByUserCodeApprove",paramMap);
	}

	@Override
	public List<BizInpAppl> selectBizInpApplByUserCodeApproveCompleted(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplListWc",paramMap);
	}

	@Override
	public List<BizInpAppl> selectBizInpApplByUserCodeCheckup(Map<String, Object> paramMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplByUserCodeCheckup",paramMap);
	}

	@Override
	public List<BizInpAppl> selectBizInpApplApprove(Map<String, Object> dataMap) {
		return getSqlMap().queryForList(NAMESPACES + "selectBizInpApplApprove",dataMap);
	}

	@Override
	public int selectBizInpApplApproveCount(Map<String, Object> record) {
		return getSqlMap().queryForObject(NAMESPACES + "selectBizInpApplApproveCount", record);
	}

	@Override
	public int selectCountCheckup(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountCheckup", map);
	}

	@Override
	public int selectZxMemerJobCount(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectZxMemerJobCount", paramMap);
	}

	@Override
	public Map<String,Object> queryYdjFlag(Map appIdMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryYdjFlag",appIdMap);
	}
	@Override
	public int selectZxMemerJobFinishedCount(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectZxMemerJobFinishedCount", paramMap);
	}

	@Override
	public int selectApprovefinishedCount(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectApprovefinishedCount", paramMap);
	}

	@Override
	public Map<String, String> selectExamineLibraryCheckMessage(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectExamineLibraryCheckMessage", map);
	}

	@Override
	public int queryFicoYdjCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES+"queryFicoYdjCount",map);
	}

	@Override
	public int queryFicoBzkCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES+"queryFicoBzkCount",map);
	}

	@Override
	public int countUnFinishByCondition(Map map) {
		return getSqlMap().queryForObject(NAMESPACES+"countUnFinishByCondition", map);
	}

	@Override
	public int selectApplyCurrentLinkCountByMap(Map<String, Object> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES+"selectApplyCurrentLinkCountByMap", paramMap);
	}

	@Override
	public Map selectAllotApplyCurrStatusByMapForExamine(Map<String, Object> conditionMap) {
		return getSqlMap().queryForObject(NAMESPACES+"selectAllotApplyCurrStatusByMapForExamine", conditionMap);
	}

	@Override
	public String selectMatchingCardFlag(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectMatchingCardFlag", appId);
	}

	@Override
	public List<String> queryQrcodeList(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectQrcodeList", appId);
	}

	@Override
	public String queryQrcode(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectQrcode", appId);
	}
	
}
