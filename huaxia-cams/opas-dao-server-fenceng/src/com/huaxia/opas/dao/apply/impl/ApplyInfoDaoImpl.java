package com.huaxia.opas.dao.apply.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.rule.OpasBizApproval;

public class ApplyInfoDaoImpl extends AbstractDAO implements ApplyInfoDao {

	private static final long serialVersionUID = 2045015690369777708L;

	private static final String NAMESPACES = "ApplyInformation.";
	private static final String NAMESPACES1 = "ApplyLifeCicle.";
	private static final String NAMESPACES2 = "ApplyModifyLog.";
	private static final String NAMESPACES3 = "BizInpApplC1.";

	@Override
	public int selectCurrentApplyCount1(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrentApplyCount1", params);
	}

	@Override
	public int selectCurrentApplyCount2(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrentApplyCount2", params);
	}

	@Override
	public int selectCurrentApplyCount3(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrentApplyCount3", params);
	}

	@Override
	public List<Map<String, String>> selectCurrentApplyList1(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectCurrentApplyList1", params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectCurrentApplyList2(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectCurrentApplyList2", params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectCurrentApplyList3(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectCurrentApplyList3", params, curNum, pageNum);
	}

	@Override
	public int selectHistoryCount1(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectHistoryCount1", params);
	}

	@Override
	public int selectHistoryCount2(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectHistoryCount2", params);
	}

	@Override
	public int selectHistoryCount3(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectHistoryCount3", params);
	}

	@Override
	public List<Map<String, String>> selectHistoryList1(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectHistoryList1", params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectHistoryList2(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectHistoryList2", params, curNum, pageNum);
	}

	@Override
	public List<Map<String, String>> selectHistoryList3(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectHistoryList3", params, curNum, pageNum);
	}

	@Override
	public List<Map<String, Object>> selectCurrentState() {
		return getSqlMap().queryForList(NAMESPACES + "selectCurrentState");
	}

	@Override
	public List<Map<String, Object>> selectHistoryState() {
		return getSqlMap().queryForList(NAMESPACES + "selectHistoryState");
	}

	@Override
	public int selectHighCurrentCount(Map<String, Object> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectHighCurrentCount", params);
	}

	@Override
	public List<Map<String, Object>> selectHighCurrentList(Map<String, Object> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectHighCurrentList", params, curNum, pageNum);
	}

	@Override
	public int selectHighHistoryCount(Map<String, Object> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectHighHistoryCount", params);
	}

	@Override
	public List<Map<String, Object>> selectHighHistoryList(Map<String, Object> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectHighHistoryList", params, curNum, pageNum);
	}

	@Override
	public int selectArchiveCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectArchiveCount", params);
	}

	@Override
	public List<Map<String, String>> selectCurrentBIZC1Info(Map<String, String> params) {
		return getSqlMap().queryForList(NAMESPACES + "selectCurrentBIZC1Info", params);
	}

	@Override
	public int selectCurrentVVIP(Map<String, String> applyMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrentVVIP", applyMap);
	}

	@Override
	public int selectCurrentArchive(Map<String, String> applyMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCurrentArchive", applyMap);
	}

	@Override
	public Map<String, String> selectApplyCompanyInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectApplyCompanyInfo", appId);
	}

	@Override
	public BizInpApplC1 queryBizInpApplC1ByAppId(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryBizInpApplC1ByAppId", appId);
	}
	
	@Override
	public BizInpApplC1 queryBizInpApplC1C2ByAppId(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryBizInpApplC1C2ByAppId", appId);
	}
	
	@Override
	public BizInpAppC1Spec queryBizInpApplC1ByAppId2(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryBizInpApplC1ByAppId2", appId);
	}
	
	@Override
	public BizInpAppC1Spec queryBizInpApplC1ByAppId2New(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryBizInpApplC1ByAppId2New", appId);
	}

	@Override
	public void updateBizInfo(BizInpApplC1 bizInpApplC1) throws CoreException {

		getSqlMap().update(NAMESPACES3 + "updateBizInfo", bizInpApplC1);
	}

	@Override
	public BizInpApplC1 selectBizInpApplC1ByAppId(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "selectBizInpApplC1ByAppId", appId);
	}

	@Override
	public BizInpApplC2 findBiz2info(String appno) throws CoreException {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES3 + "findBiz2info", appno);
	}

	@Override
	public int queryCount(Map<String, Object> map) throws CoreException {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject(NAMESPACES3 + "queryCount", map);
	}

	@Override
	public List<Map<Object, Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException {

		return getSqlMap().queryForList(NAMESPACES3 + "queryList", map, curNum, pageNum);
	}

	@Override
	public Map queryDragAqqlyById(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryDragAqqlyById", appId);
	}

	@Override
	public List<ApplyLifeCicle> queryApplyLifeList(String appId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES1 + "queryApplyLifeList", appId);
	}

	@Override
	public void saveApplyModifyLog(ApplyModifyLog applyModifyLog) throws CoreException {
		getSqlMap().insert(NAMESPACES2 + "insertModifyLog", applyModifyLog);
	}

	@Override
	public void updateApply2Info(BizInpApplC2 keyMesageApply2) {
		getSqlMap().update(NAMESPACES3 + "updateApply2Info", keyMesageApply2);
	}

	@Override
	public Map queryInDataChecK(String appId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryInDataChecK", appId);
	}

	// 4-2
	@Override
	public List<BizInpApplC1>  queryBizAppToFish(String appId) {
		return getSqlMap().queryForList(NAMESPACES3 + "queryBizAppToFish", appId);
	}

	@Override
	public OpasBizApproval queryHisallot(String appId) {
		OpasBizApproval hisallot = null;
		List<OpasBizApproval> list = getSqlMap().queryForList(NAMESPACES3 + "queryHisallot", appId);
		if (list != null && list.size() > 0) {
			hisallot = list.get(0);
		}
		return hisallot;
	}

	@Override
	public void deleteIcationById(String appId) {
		getSqlMap().delete(NAMESPACES3 + "deleteIcationById", appId);
	}

	@Override
	public Map<String, String> queryIcationByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryIcationByAppId", appId);
	}

	@Override
	public void updateApplyModify(ApplyModifyLog applyModifyLog) {
		getSqlMap().update(NAMESPACES2 + "updateApplyModify", applyModifyLog);
	}

	@Override
	public ApplyModifyLog queryApplyModify(ApplyModifyLog applyModifyLog) {
		return getSqlMap().queryForObject(NAMESPACES2 + "queryApplyModify", applyModifyLog);
	}

	@Override
	public List<Map<String, Object>> selectHighAppayState() {
		return getSqlMap().queryForList(NAMESPACES + "selectHighAppayState");
	}

	@Override
	public List<Map<String, Object>> selectHighHistoryAppayState() {
		return getSqlMap().queryForList(NAMESPACES + "selectHighHistoryAppayState");
	}

	@Override
	public int selectNodeCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectNodeCount", params);
	}

	@Override
	public List<Map<String, Object>> selectNodeList(Map<String, String> params, int curNum, int pageNum) {
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "selectNodeList", params, curNum,
				pageNum);
		return queryForList;
	}

	@Override
	public int selectRemarkCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "selectRemarkCount", params);
	}

	@Override
	public List<Map<String, Object>> selectRemarkList(Map<String, String> params, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "selectRemarkList", params, curNum, pageNum);
	}


	@Override
	public List<Map<String, Object>> selectExprotCurrentBJInfo(List<String> appIds) {
		return getSqlMap().queryForList(NAMESPACES + "selectExprotCurrentBJInfo", appIds);
	}

	@Override
	public List<Map<String, Object>> selectExprotHistoryBJInfo(List<String> appIds) {
		return getSqlMap().queryForList(NAMESPACES + "selectExprotHistoryBJInfo", appIds);
	}

	@Override
	public void updateAllotApply(Map mapParms) {
		getSqlMap().update(NAMESPACES3 + "updateAllotApply", mapParms);
	}

	@Override
	public List<Map> selectAppByReviewDecision(List<String> appIdList) {
		return getSqlMap().queryForList(NAMESPACES3 + "selectAppByReviewDecision", appIdList);
	}

	@Override
	public List queryDescByAppId(Map map,int curNum,int pageNum)
			throws CoreException {
		return getSqlMap().queryForList(NAMESPACES3 + "queryDescByAppId", map,curNum,pageNum);
	}
	@Override
	public int queryCountDesc(Map map) throws CoreException {
		
		return getSqlMap().queryForObject(NAMESPACES3 + "queryCountDesc", map);
	}

	@Override
	public Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId) {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryMatchingCardFlagAndYdjFalg", appId);
	}

	@Override
	public List<Map<String, String>> queryMatchingCardFlagAndYdjFalgByIdNbr(Map<String, String> matchingCard) {
		return  getSqlMap().queryForList(NAMESPACES3 + "queryMatchingCardFlagAndYdjFalgByIdNbr", matchingCard);
	}

	@Override
	public void updateBizInfoMatchingCard(BizInpAppC1Spec updateBizInpApplC1) {
		getSqlMap().update(NAMESPACES3 + "updateBizInfoMatchingCard",updateBizInpApplC1);
	}
	
	@Override
	public void updateBizInfoMatchingCardBzk(BizInpAppC1Spec updateBizInpApplC1) {
		getSqlMap().update(NAMESPACES3 + "updateBizInfoMatchingCardBzk",updateBizInpApplC1);
	}
	
	@Override
	public void updateBizInfoMatchingCardYdj(BizInpAppC1Spec updateBizInpApplC1) {
		getSqlMap().update(NAMESPACES3 + "updateBizInfoMatchingCardYdj",updateBizInpApplC1);
	}

	@Override
	public Map<String, String> queryFicoBigResponse(String appId) {
		return getSqlMap().queryForObject(NAMESPACES3 + "queryFicoBigResponse", appId);
	}

	@Override
	public void updateBizInfoKeyMes(BizInpAppC1Spec keyMesageApply1) {
		 getSqlMap().queryForObject(NAMESPACES3 + "updateBizInfoKeyMes", keyMesageApply1);
	}

	@Override
	public int queryApplyInfoCount(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApplyInfoCount", map);
	}

	@Override
	public List<Map<String, String>> queryApplyInfoMap(Map<String, Object> map, int curNum, int pageNum)
			throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryApplyInfoMap", map, curNum, pageNum);
	}
	
	@Override
	public void updateBizC1C4VCODE(String string) {
		getSqlMap().update(NAMESPACES3+"updateBizC1C4VCODE", string);
	}

	@Override
	public void insertApplyLifeCicle(ApplyLifeCicle alc) {
		getSqlMap().update(NAMESPACES3+"insertApplyLifeCicle", alc);
	}

	@Override
	public AllotApply queryApplyAllotByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES3+"queryApplyAllotByAppId",appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoYdj(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"selectExprotCurrentInfoYdj",appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoBzk(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES+"selectExprotCurrentInfoBzk",appId);
	}

	@Override
	public Map<String, String> selectAllotMap(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectAllotMap",appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoYdjHis(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectExprotCurrentInfoYdjHis",appId);
	}

	@Override
	public Map<String, Object> selectExprotCurrentInfoBzkHis(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectExprotCurrentInfoBzkHis",appId);
	}

	@Override
	public void updateBizInfoKeyMes2(BizInpApplC2 keyMesageApply2) {
		getSqlMap().queryForObject(NAMESPACES3 + "updateBizInfoKeyMes2", keyMesageApply2);
	}

	@Override
	public int queryApplyInfoCountWithPatchCode(Map<String, Object> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryApplyInfoCountWithPatchCode", params);
	}

	@Override
	public List<Map<String, String>> queryApplyInfoMapWithPatchCode(Map<String, Object> params, int curNum,
			int pageNum) {
		return  getSqlMap().queryForList(NAMESPACES + "queryApplyInfoMapWithPatchCode", params, curNum, pageNum);
	}

	@Override
	public String queryApplyLifeCicle(String appIdPlus) {
		return  getSqlMap().queryForObject(NAMESPACES + "queryApplyLifeCicle",appIdPlus);
	}

	@Override
	public int queryWsdbInfoCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWsdbInfoCount", map);
	}

	@Override
	public List<Map<String, String>> queryWsdbInfoMap(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryWsdbInfoMap", map, curNum, pageNum);
	}

	@Override
	public int queryWsdbNodeCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWsdbNodeCount", params);
	}

	@Override
	public List<Map<String, Object>> selectWsdbNodeList(Map<String, String> params, int curNum, int pageNum) {
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "selectWsdbNodeList", params, curNum,
				pageNum);
		return queryForList;
	}

	@Override
	public Map<String, Object> selectWsdbInfoByAppId(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectWsdbInfoByAppId",appId);
	}
	
	@Override
	public int queryStockCustomerInfoCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryStockCustomerInfoCount", map);
	}

	@Override
	public List<Map<String, String>> queryStockCustomerInfoMap(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryStockCustomerInfoMap", map, curNum, pageNum);
	}

	@Override
	public int queryStockCustomerNodeCount(Map<String, String> params) {
		return getSqlMap().queryForObject(NAMESPACES + "queryStockCustomerNodeCount", params);
	}

	@Override
	public List<Map<String, Object>> selectStockCustomerNodeList(Map<String, String> params, int curNum, int pageNum) {
		List<Map<String, Object>> queryForList = getSqlMap().queryForList(NAMESPACES + "selectStockCustomerNodeList", params, curNum,
				pageNum);
		return queryForList;
	}

	@Override
	public Map<String, Object> selectStockCustomerInfoByAppId(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectStockCustomerInfoByAppId",appId);
	}

	@Override
	public List<Map<String,Object>> selectStockCustomerCardInfoByAppId(String appId) {
		return  getSqlMap().queryForList(NAMESPACES + "selectStockCustomerCardInfoByAppId",appId);
	}
	@Override
	public Map<String, String> selectStockCustomerCardResultByAppId(String appId) {
		return  getSqlMap().queryForObject(NAMESPACES + "selectStockCustomerCardResultByAppId",appId);
	}
}
