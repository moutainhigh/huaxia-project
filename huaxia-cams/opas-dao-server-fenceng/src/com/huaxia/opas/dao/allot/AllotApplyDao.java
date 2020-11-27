package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.TempAllot;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.report.KeyMessageModify;
import com.huaxia.opas.domain.thirdparty.BizApproval;

public interface AllotApplyDao {
	//定时批量导入申请件到分配
	List<AllotApply> selectYdjAppFromC1() throws CoreException;
	
	List<AllotApply> selectBzkAppFromC1() throws CoreException;
	
	int insertAppBatch(List<AllotApply> list) throws CoreException;
	
	int updateC1Batch(List<AllotApply> list) throws CoreException;
	
	//手动查询
	List<String> selectReviewApplication(Map map) throws CoreException;
	
	List<String> selectYdjTelAppProd(Map map) throws CoreException;
	
	List<String> selectReviewAppByIVS(Map map) throws CoreException;
	
	List<String> selectReviewAppByScor(Map map) throws CoreException;
	
	List<String> selectReviewAppByCue(Map map) throws CoreException;
	
	List<String> selectBzkAppByBlaze(Map map) throws CoreException;
	
	List<String> selectYdjAppByBlaze(Map map) throws CoreException;
	
	List<String> selectCreditAppByBzkDecision(Map map) throws CoreException;
	
	List<String> selectCreditAppByYdjDecision(Map map) throws CoreException;
	
	List<String> selectAppByReviewDecision(Map map) throws CoreException;
	
	//查询排序
	List<AllotApply> selectAppNoByOrder(Map map) throws CoreException;
	
	List<AllotApply> selectAppListByOrder(Map map,int page, int rows) throws CoreException;
	
	List<AllotApply> selectAppListByOrder(Map map) throws CoreException;
	
	int selectCountByOrder(Map map) throws CoreException;
	//征信修改关键信息件
	List<KeyMessageModify> selectKeyMessageApp() throws CoreException;
	//批量分配
	int updateBatchApply(List<AllotApply> list) throws CoreException;
	
	int updateBatchList(AllotApply  allotApply) throws CoreException;
	//end
	// 插入
	int insertAllotApply(AllotApply allotApply) throws CoreException;

	// 节点之间更新
	int update(AllotApply allotApply) throws CoreException;
	
	// 同组组员间转移 
	int updateAllotApply(AllotApply allotApply) throws CoreException;
	
	//不同层级回收
	int updateByPrimaryKey(AllotApply allotApply) throws CoreException;
	
	int updateBatchByIds(List<AllotApply> list) throws CoreException;
	//int updateBatchByIds(AllotApply allotApply) throws CoreException;
	//多表综合  start
	int selectCount(Map map) throws CoreException;
	
	// 组编号查询未分配件
	
	List<AllotApply> selectAllotApplyByGroup(Map map, int page, int rows) throws CoreException;

	List<AllotApply> selectAllotApplyByGroup(Map map) throws CoreException;

	// 组员编号查询剩余件个数
	
	List<AllotApply> selectAllotApplyByUser(Map map) throws CoreException;

	// 团办号去重
	List<AllotApply> selectC4ApBatch(Map map,int page, int rows) throws CoreException;
	
	List<AllotApply> selectC5JCType(Map map,int page, int rows) throws CoreException;

	int countC4ApBatch(Map map) throws CoreException;
	
	int countC5JCType(Map map) throws CoreException;
	
	// 推广机构
	List<AllotApply> selectC5AbCode(Map map,int page, int rows) throws CoreException;

	int countC5AbCode(Map map) throws CoreException;
	
	// 推广人员
	List<AllotApply> selectC4AbUser(Map map,int page, int rows) throws CoreException;

	int countC4AbUser(Map map) throws CoreException;
	
	// 手动分件按申请件 编号
	int selectHandCount(String appId) throws CoreException;
	
	List<AllotApply> selectApplyListByAppId(String appId) throws CoreException;
	
	List<AllotApply> selectHand(Map map) throws CoreException;

	AllotApply selectHandByAppId(Map map) throws CoreException;
	
	AllotApply selectSingleByAppId(Map map) throws CoreException;
	
	int selectHandCount(Map map) throws CoreException;

	// 获取自动
	List<String> selectYdjTelApplication(Map map) throws CoreException;
	
	//征信批量
	int selectCountBlockCode(String appId) throws CoreException;
	
	TelcheckResult selectBlockCodeList(Map map) throws CoreException;
	
	int updateBlockCode(Map map) throws CoreException;
	
	int insertBlockCode(Map map) throws CoreException;
	
	//审批批量
	int selectCountRefuseCode(String appId) throws CoreException;
	
	BizApproval selectRefuseCodeList(Map map) throws CoreException;
	
	int updateRefuseCode(Map map) throws CoreException;
	
	int insertRefuseCode(Map map) throws CoreException;
	
	int insertRefuseCodeBatch(List<BizApproval> list) throws CoreException;
	
	//当日新接收的申请件
	int selectTodayApp(Map map) throws CoreException;
	
	//插入临时表  
	int insertTemp(Map map) throws CoreException;
	
	List<AllotApply> executeOpasPoTempAllot(Map map);
	
	int insertTempBatch(List<TempAllot> list) throws CoreException;
	
	int insertTempTab(List<TempAllot> list) throws CoreException;
	
	List<AllotApply> selectAppNoByTemp(Map map) throws CoreException;
	
	//插入临时分配主表
	int saveBatchByTemp(List<AllotApply> list) throws CoreException;
	
	//根据临时分配主表 同步更新到分配表
	int updateBatchByTemp(List<String> list) throws CoreException;
	
	// 特定征信到特定审批
	List<AllotApply> selectApplyBySpecialMen(Map map) throws CoreException;
	
	Map selectApplC2(String appId) throws CoreException;
	//标准卡综合分件查询
	int selectBzkCountByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectBzkByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectBzkByConditions(Map map,int page, int rows) throws CoreException;
	//易达金综合分件查询
	int selectYdjCountByConditions(Map map) throws CoreException;
	
	int selectYdjNumByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectYdjByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectYdjByConditions(Map map,int page, int rows) throws CoreException;
	
	List<AllotApply> selectYdjAppNoByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectYdjAppNoByConditions(Map map,int page, int rows) throws CoreException;
	//反欺诈综合分件查询
	int selectFraudCountByConditions(Map map) throws CoreException;
	
	int selectFraudNumByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectFraudByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectFraudByConditions(Map map,int page, int rows) throws CoreException;
	
	List<AllotApply> selectFraudAppNoByConditions(Map map) throws CoreException;
	
	List<AllotApply> selectFraudAppNoByConditions(Map map,int page, int rows) throws CoreException;
	//时间同步
	int updateLstOptTime(Map map) throws CoreException;

//	int selectCountByUserCode(Map map) throws CoreException;
	
	int queryWaitingEnterCount(Map<String, Object> params);
	
	List<Map<String, Object>> queryWaitingEnterList(Map<String, Object> params, int curNum, int pageNum);
	
	/**
	 * 欺诈回收时，批量修改欺诈提报表信息
	 * @author wenyh
	 * @param list
	 * @return
	 * @throws CoreException
	 */
	int updateBatchSubmitFraud(List<AllotApply> list) throws CoreException;
}
