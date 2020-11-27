package com.huaxia.opas.dao.apply;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.rule.OpasBizApproval;
import com.huaxia.opas.domain.system.ApOnline;

public interface ApplyInfoDao {


	/**
	 * 申请件信息一般查询
	 * 
	 * @param params
	 * @return
	 */
	int selectCurrentApplyCount1(Map<String, String> params);
	int selectCurrentApplyCount2(Map<String, String> params);
	int selectCurrentApplyCount3(Map<String, String> params);

	List<Map<String, String>> selectCurrentApplyList1(Map<String, String> params, int curNum, int pageNum);
	List<Map<String, String>> selectCurrentApplyList2(Map<String, String> params, int curNum, int pageNum);
	List<Map<String, String>> selectCurrentApplyList3(Map<String, String> params, int curNum, int pageNum);
	
	public int queryApplyInfoCount(Map<String, Object> map)throws CoreException;
	public List<Map<String, String>> queryApplyInfoMap(Map<String, Object> map, int curNum, int pageNum)throws CoreException;

	/**
	 * 申请件历史信息查询
	 * 
	 * @param params
	 * @return
	 */
	int selectHistoryCount1(Map<String, String> params);
	int selectHistoryCount2(Map<String, String> params);
	int selectHistoryCount3(Map<String, String> params);

	List<Map<String, String>> selectHistoryList1(Map<String, String> params, int curNum, int pageNum);
	List<Map<String, String>> selectHistoryList2(Map<String, String> params, int curNum, int pageNum);
	List<Map<String, String>> selectHistoryList3(Map<String, String> params, int curNum, int pageNum);

	/**
	 * 申请件信息状态查询
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectCurrentState();

	/**
	 * 申请件信息历史状态查询
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectHistoryState();

	/**
	 * 申请件高级当前查询
	 * 
	 * @param params
	 * @return
	 */
	int selectHighCurrentCount(Map<String, Object> params);

	List<Map<String, Object>> selectHighCurrentList(Map<String, Object> params, int curNum, int pageNum);

	/**
	 * 申请件高级历史查询
	 * 
	 * @param params
	 * @return
	 */
	int selectHighHistoryCount(Map<String, Object> params);

	List<Map<String, Object>> selectHighHistoryList(Map<String, Object> params, int curNum, int pageNum);
	/**
	 * VVIP客户历史信息数量查询
	 * @param c1Idnbr
	 * @return
	 */
	public int selectArchiveCount(Map<String,String> params);
	/**
	 * 主卡中客户信息查询 
	 * @param params
	 * @return
	 */
	List<Map<String, String>> selectCurrentBIZC1Info(Map<String, String> params);
	/**
	 * VVIP客户信息数量查询
	 * @param applyMap
	 * @return
	 */
	int selectCurrentVVIP(Map<String, String> applyMap);
	/**
	 * 查询申请件是否归档
	 * @param applyMap
	 * @return
	 */
	int selectCurrentArchive(Map<String, String> applyMap);
	/**
	 * 查询申请件单位信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 单位信息（公司行业类别码、公司中文全称、单位地址）
	 */
	Map<String, String> selectApplyCompanyInfo(String appId);
	
	/**
	 * 申请件信息
	 * @param appId
	 * @return
	 * @throws CoreException
	 * jiangming.yang
	 */
	BizInpApplC1 queryBizInpApplC1ByAppId(String appId) throws CoreException;
	
	BizInpApplC1 queryBizInpApplC1C2ByAppId(String appId) throws CoreException;
	
	BizInpAppC1Spec queryBizInpApplC1ByAppId2(String appId) throws CoreException;
	
	BizInpAppC1Spec queryBizInpApplC1ByAppId2New(String appId) throws CoreException;

	void updateBizInfo(BizInpApplC1 bizInpApplC1) throws CoreException;
	
	BizInpApplC1 selectBizInpApplC1ByAppId(String appId) throws CoreException;
	 
	BizInpApplC2 findBiz2info(String appno) throws CoreException;

	int queryCount(Map<String, Object> map)throws CoreException;

	List<Map<Object,Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException;

	Map queryDragAqqlyById(String appId)throws CoreException;

	List<ApplyLifeCicle> queryApplyLifeList(String appId)throws CoreException;

	void saveApplyModifyLog(ApplyModifyLog applyModifyLog) throws CoreException;

	void updateApply2Info(BizInpApplC2 keyMesageApply2);
	
	Map queryInDataChecK(String appId) throws CoreException;
	
	List<BizInpApplC1>  queryBizAppToFish(String appId);

	OpasBizApproval queryHisallot(String appId);

	void deleteIcationById(String appId);

	Map<String, String> queryIcationByAppId(String appId);
	List queryDescByAppId(Map map,int curNum,int pageNum)throws CoreException;
	int queryCountDesc(Map map)throws CoreException;
	void updateApplyModify(ApplyModifyLog applyModifyLog);

	ApplyModifyLog queryApplyModify(ApplyModifyLog applyModifyLog);
	/**
	 * 审批结果状态查询
	 * @return
	 */
	List<Map<String, Object>> selectHighAppayState();
	/**
	 * 高级查询之审批结果状态查询
	 * @return
	 */
	List<Map<String, Object>> selectHighHistoryAppayState();
	/**
	 * 申请件流程节点显示
	 * @param params
	 * @return
	 */
	int selectNodeCount(Map<String, String> params);

	List<Map<String, Object>> selectNodeList(Map<String, String> params, int curNum, int pageNum);
	/**
	 * 申请件全局信息备注信息查询
	 * @param params
	 * @return
	 */
	int selectRemarkCount(Map<String, String> params);

	List<Map<String, Object>> selectRemarkList(Map<String, String> params, int curNum, int pageNum);
	

	/**
	 * 补件内容查询
	 * @param appIds
	 * @return
	 */
	List<Map<String, Object>> selectExprotCurrentBJInfo(List<String> appIds);
	
	List<Map<String, Object>> selectExprotHistoryBJInfo(List<String> appIds);
	
	void updateAllotApply(Map mapParms);
	
	List<Map> selectAppByReviewDecision(List<String> appIdList);
	
	Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId);
	
	List<Map<String, String>> queryMatchingCardFlagAndYdjFalgByIdNbr(Map<String, String> matchingCard);
	
	void updateBizInfoMatchingCard(BizInpAppC1Spec updateBizInpApplC1);
	
	void updateBizInfoMatchingCardBzk(BizInpAppC1Spec updateBizInpApplC1);
	
	void updateBizInfoMatchingCardYdj(BizInpAppC1Spec updateBizInpApplC1);
	
	Map<String, String> queryFicoBigResponse(String appId);
	
	void updateBizInfoKeyMes(BizInpAppC1Spec keyMesageApply1);
	
	void updateBizC1C4VCODE(String string);
	
	void insertApplyLifeCicle(ApplyLifeCicle alc);
	
	AllotApply queryApplyAllotByAppId(String appId);
	
	Map<String, Object> selectExprotCurrentInfoYdj(String appId);
	
	Map<String, Object> selectExprotCurrentInfoBzk(String appId);
	//根据appId去查询ydjFlag和matchingCardFlag
	Map<String, String> selectAllotMap(String appId);
	
	Map<String, Object> selectExprotCurrentInfoYdjHis(String appId);
	
	Map<String, Object> selectExprotCurrentInfoBzkHis(String appId);
	
	void updateBizInfoKeyMes2(BizInpApplC2 keyMesageApply2);
	int queryApplyInfoCountWithPatchCode(Map<String, Object> params);
	List<Map<String, String>> queryApplyInfoMapWithPatchCode(Map<String, Object> params, int curNum, int pageNum);
	String queryApplyLifeCicle(String appIdPlus);
	//网申短表相关查询
	int queryWsdbInfoCount(Map<String, Object> map);
	List<Map<String, String>> queryWsdbInfoMap(Map<String, Object> map, int curNum, int pageNum);
	int queryWsdbNodeCount(Map<String, String> params);
	List<Map<String, Object>> selectWsdbNodeList(Map<String, String> params, int curNum, int pageNum);
	Map<String, Object> selectWsdbInfoByAppId(String appId);
	
	//存量客户相关查询
	int queryStockCustomerInfoCount(Map<String, Object> map);
	List<Map<String, String>> queryStockCustomerInfoMap(Map<String, Object> map, int curNum, int pageNum);
	int queryStockCustomerNodeCount(Map<String, String> params);
	List<Map<String, Object>> selectStockCustomerNodeList(Map<String, String> params, int curNum, int pageNum);
	Map<String, Object> selectStockCustomerInfoByAppId(String appId);
	List<Map<String,Object>> selectStockCustomerCardInfoByAppId(String appId);
	Map<String, String> selectStockCustomerCardResultByAppId(String appId);
}
