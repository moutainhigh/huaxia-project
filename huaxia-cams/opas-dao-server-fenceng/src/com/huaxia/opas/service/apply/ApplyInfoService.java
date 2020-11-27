package com.huaxia.opas.service.apply;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;
import com.huaxia.opas.domain.input.BizInpAppC1Spec;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplC2;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;

public interface ApplyInfoService {

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
	 *申请件信息状态查询 
	 * @return
	 */
	List<Map<String, Object>> selectCurrentState();

	/**
	 * 申请件信息历史状态查询
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
	 * @param string
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
	Map<String, String> queryApplyCompanyInfo(String appId);
	
	/**
	 * 申请表信息页面
	 * @param bizInpApplC1
	 * @throws CoreException
	 * @author: jiangming.yang
	 */
	void updateBizInfo(BizInpAppC1Spec bizInpApplC1,List<BaseCustInfo> bcfList) throws CoreException;
	
	void updateBizInfoBzk(BizInpAppC1Spec bizInpApplC1,List<BaseCustInfo> bcfList) throws CoreException;
	
	void updateBizInfoYdj(BizInpAppC1Spec bizInpApplC1,List<BaseCustInfo> bcfList) throws CoreException;
	
	BizInpApplC2 findBiz2info(String appno) throws CoreException;

	int queryCount(Map<String, Object> map) throws CoreException;

	List<Map<Object,Object>> queryList(Map<String, Object> map, int curNum, int pageNum) throws CoreException;

	Map queryDragAqqlyById(String appId) throws CoreException;

	List<ApplyLifeCicle> queryApplyLifeList(String appId) throws CoreException;

	void saveApplyModifyLog(List<ApplyModifyLog> modifyList) throws CoreException;

	void updateApply2Info(BizInpApplC2 keyMesageApply2) throws CoreException;
	
	BizInpApplC1 get(BizInpApplC1 t) throws CoreException;
	
	BizInpAppC1Spec get2(BizInpAppC1Spec t) throws CoreException;
	
	BizInpAppC1Spec get2New(BizInpAppC1Spec t) throws CoreException;
	
	//BizInpApplC1 queryBizAppToFish(String appId);

	//Map<String, String> queryHisallot(String appId);

	//void deleteIcationById(String appId) throws CoreException;

	//Map<String, String> queryIcationByAppId(String appId);
	/**
	 * 高级查询之审批结果状态查询
	 * @return
	 */
	List<Map<String, Object>> selectHighAppayState();
	/**
	 * 高级查询之审批结果历史状态查询
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
	
	void doDragApply(String appId,Map<String, String> paroperMap) throws CoreException, Exception;
	
	/**
	 * 更新新分件表
	 * @param appId
	 */
	
	void updateApplyAllotAndInsert(Map<String, String> matchingCard);
	
	Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId);
	
	List<Map<String, String>> queryMatchingCardFlagAndYdjFalgByIdNbr(Map<String, String> matchingCard);
	
	void updateBizInfoMatchingCard(BizInpAppC1Spec updateBizInpApplC1,List<BaseCustInfo> bcfList);
	
	void updateBizInfoMatchingCardBzk(BizInpAppC1Spec updateBizInpApplC1, List<BaseCustInfo> bcfList);
	
	void updateBizInfoMatchingCardYdj(BizInpAppC1Spec updateBizInpApplC1, List<BaseCustInfo> bcfList);
	
	List<Map<String, String>> queryBaseCustByIdNbr(Map<String, String> matchingCard);
	
	void updateBizInfoKeyMes(BizInpAppC1Spec keyMesageApply1);
	
	void saveRiskCheck(List<RiskCheckResult> listriskcheckresult, BizInpApplC1 bizInpApplC1);
	
	AllotApply queryApplyAllotByAppId(String appId);
	
	String qeurUserNameByUserCode(String user);
	
	void insertApplyLifeCicle(ApplyLifeCicle alc);
	/**
	 * 根据apId去查询ydjFla和matchingCardFlag
	 * @param appId
	 * @return
	 */
	Map<String,String> selectAllotMap(String appId);
	/**
	 * 查询易达金的导出信息
	 * @param appId
	 * @return
	 */
	Map<String, Object> selectExprotCurrentInfoYdj(String appId);
	/**
	 * 查询标准卡的导出信息
	 * @param appId
	 * @return
	 */
	Map<String, Object> selectExprotCurrentInfoBzk(String appId);
	/**
	 *查询历史易达金的导出信息
	 * @param appId
	 * @return
	 */
	Map<String, Object> selectExprotCurrentInfoYdjHis(String appId);
	/**
	 * 查询历史标准卡的导出信息
	 * @param appId
	 * @return
	 */
	Map<String, Object> selectExprotCurrentInfoBzkHis(String appId);
	
	void updateBizInfoKeyMes2(BizInpApplC2 keyMesageApply2);
	
	int queryApplyInfoCountWithPatchCode(Map<String, Object> params);
	List<Map<String, String>> queryApplyInfoMapWithPatchCode(Map<String, Object> params, int curNum, int pageNum);
	String queryApplyLifeCicle(String appIdPlus);
	
	
	/**
	 * 20200522 
	 * 网申短表数量及集合查询
	 * @param map
	 * @return
	 * @throws CoreException
	 */
	public int queryWsdbInfoCount(Map<String, Object> map)throws CoreException;
	
	public List<Map<String, String>> queryWsdbInfoMap(Map<String, Object> map, int curNum, int pageNum)throws CoreException;
	/**
	 * 网申短表流程节点显示
	 * @param params
	 * @return
	 */
	int queryWsdbNodeCount(Map<String, String> params);
	List<Map<String, Object>> selectWsdbNodeList(Map<String, String> params, int curNum, int pageNum);
	Map<String, Object> selectWsdbInfoByAppId(String appId);

	/**
	 * 2020808 
	 * 存量客户数量及集合查询
	 * @param map
	 * @return
	 * @throws CoreException
	 */
	public int queryStockCustomerInfoCount(Map<String, Object> map)throws CoreException;
	
	public List<Map<String, String>> queryStockCustomerInfoMap(Map<String, Object> map, int curNum, int pageNum)throws CoreException;
	/**
	 * 存量客户流程节点显示
	 * @param params
	 * @return
	 */
	int queryStockCustomerNodeCount(Map<String, String> params);
	List<Map<String, Object>> selectStockCustomerNodeList(Map<String, String> params, int curNum, int pageNum);
	/**
	 * 存量客户详细信息查询
	 * @param appId
	 * @return
	 */
	Map<String, Object> selectStockCustomerInfoByAppId(String appId);
	/**
	 * 查询存量客户联机发卡信息
	 * @param appId
	 * @return
	 */
	List<Object> selectStockCustomerCardInfoByAppId(String appId);
}
