package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.thirdparty.BizApproval;

public interface AllotApplyService  {
	
	 //申请件批量及标识打标
	 int updateFico(Map map) ;
	//多条件查询
	 
	Map<String,Object> saveReviewPoolByCondition(Map<String,Object> map)
			throws CoreException ;
	//批量分配 (三种方式  效率不一)
	int updateBatchApply(List<AllotApply> list) throws CoreException;
	
	int updateBatchList(AllotApply  allotApply) throws CoreException;
	
	int updateBatchByTemp(List<AllotApply> list,List<String> lifeList) throws CoreException;
	// 插入
	int saveAllotApply(AllotApply allotApply) throws CoreException;
	
	// 同组组员间转移 
	int updateAllotApply(AllotApply allotApply) throws CoreException;
	
	//不同层级回收
	int updateByPrimaryKey(AllotApply allotApply) throws CoreException;
	//多表综合  
	// 征信 审批 审查
	int queryCount(Map map) throws CoreException;
	
	List<AllotApply> queryAllotApplyByGroup(Map map, int page, int rows) throws CoreException;

	List<AllotApply> queryAllotApplyByGroup(Map map) throws CoreException;

	List<AllotApply> queryAllotApplyByUser(Map<String,Object> map) throws CoreException;

	// 团办号去重
	List<AllotApply> queryC4ApBatch(Map<String,Object> map, int page, int rows) throws CoreException;
	
	List<AllotApply> queryC5JCType(Map<String,Object> map, int page, int rows) throws CoreException;

	int countC4ApBatch(Map<String,Object> map) throws CoreException;
	
	int countC5JCType(Map<String,Object> map) throws CoreException;
	
	// 推广机构
	List<AllotApply> queryC5AbCode(Map<String,Object> map, int page, int rows) throws CoreException;

	int countC5AbCode(Map<String,Object> map) throws CoreException;
	
	// 推广人员
	List<AllotApply> queryC4AbUser(Map<String,Object> map, int page, int rows) throws CoreException;

	int countC4AbUser(Map<String,Object> map) throws CoreException;
	
	// 手动分件按申请件 编号
	int queryHandCount(String appId) throws CoreException;
	
	List<AllotApply> queryApplyListByAppId(String appId) throws CoreException;
	
	List<AllotApply> queryHand(Map map) throws CoreException;

	AllotApply queryHandByAppId(Map map) throws CoreException;

	int queryHandCount(Map map) throws CoreException;
	// 获取自动
	List<AllotApply> queryAppNoByOrder(Map map) throws CoreException ;
	
	//征信批量
	int queryCountBlockCode(String appId) throws CoreException;
	
	TelcheckResult queryBlockCodeList(Map map) throws CoreException;
	
	int saveBlockCode(Map map) throws CoreException;
	
	int updateBlockCode(Map map) throws CoreException;
	//审批批量
	int queryCountRefuseCode(String appId) throws CoreException;
	
	BizApproval queryRefuseCodeList(Map map) throws CoreException;
	
	int saveRefuseCode(Map map) throws CoreException;
	
	int updateRefuseCode(Map map) throws CoreException;
	
	int saveRefuseCodeBatch(List<BizApproval> list) throws CoreException;
	
	//当日新接收的申请件
	int queryTodayApp(Map map) throws CoreException;
	
	//录入审查调工作流
	String saveNewUrlClient(String appId,String currNode) throws Exception;
	 
	 //征信  审批 调  工作流
	 String saveNewUrlClient(Map<String, Object> appMap,Client client) throws Exception;
	
	 //综合分件查询
	 Map<String,Object> saveApplyByConditions(Map<String,Object> map) throws CoreException ;
	 
	 //征信策略查询
	String queryResultByAppId(String appId) throws CoreException;
	
	//避免并发情况
	int updateReviewStatus(List<AllotApply> list) throws Exception;
	
	/**
	 * 查询待进入人工列表总记录数
	 * @param params
	 * @return
	 */
	int queryWaitingEnterCount(Map<String, Object> params);
	/**
	 * 查询待进入人工列表数据
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryWaitingEnterList(Map<String, Object> params, int curNum, int pageNum);
}
