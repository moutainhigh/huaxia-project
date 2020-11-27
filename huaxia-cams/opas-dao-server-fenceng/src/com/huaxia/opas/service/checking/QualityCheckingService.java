package com.huaxia.opas.service.checking;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.checking.QualityChecking;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.domain.system.ApUser;

public interface QualityCheckingService {

	int queryOperatorCount(Map<String, Object> map);
	/**
	 * 质检查询条件操作员队列展示
	 * @param operator 选择的是操作员/征信员/授信员/征审合一员
	 * @return
	 */
	List<ApUser> queryOperatorList(Map<String, Object> map,int curNum,int pageNum);
	
	int queryQualityCheckingCount(Map<String, Object> dataMap);
	
	/**
	 * 根据搜索条件展示质检列表
	 * @param dataMap
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	List<QualityChecking> queryQualityCheckingList(Map<String, Object> dataMap,int curNum,int pageNum);
	
	/**
	 * 保存质检记录
	 * @param qualityChecking
	 * @return
	 */
	int saveQualityChecking(QualityChecking qualityChecking);
	
	/**
	 * 根据
	 * @param object
	 * @return
	 */
	int queryQualityCheckingCountByAppId(String appId);
	
	/**
	 * 
	 * @param qualityChecking
	 * @return  //不用了，质检记录不更新只插入
	 */
//	int updateQualityChecking(QualityChecking qualityChecking);
	
	/**
	 * 根据appId查询质检结果
	 * @param appId
	 * @return
	 */
	QualityChecking selectQualityChecking(String appId);
	
	/**
	 * 根据当前的用户获取其组代码
	 * @param userUuid
	 * @return
	 */
	String queryUserOrgNo(String userUuid);
	
	/**
	 * g根据appId查询质检状态
	 * @param appId
	 * @return
	 */
	String queryStopFlag(String appId);
	
	/**
	 * 根据日期查询该日期质检得件
	 * @param paramMap
	 * @return
	 */
	List<QualityChecking> queryQualityCheckingListByDate(Map<String, Object> paramMap);
	
	/**
	 * 根据appId查询申请人及其所在岗位
	 * @param appId
	 * @return
	 */
	Map<String, String> queryPerInspected(String appId);
	
	/**
	 * 叫停的件再次质检不更新叫停状态
	 * @param qualityChecking
	 * @return 质检只插入不更新
	 */
//	int updateQualityCheckingWithoutStopFlag(QualityChecking qualityChecking);
	/**
	 * 查询拒绝码
	 * @return
	 */
	List<ApproveReasonCode> queryRefuseCode();
	/**
	 * 查询违例码
	 * @return
	 */
	List<ApproveReasonCode> queryBreakCode();
	/**
	 * 根据appId去查询申请件分配表的一些标识
	 * @param appId
	 * @return
	 */
	Map<String, Object> querySomeFlagByAppId(String appId);
	
	String queryUserUuid(String appId);
	/**
	 * 查询该组所有组员
	 * @param map
	 * @return
	 */
	List<String> queryOperatorAllList(Map<String, Object> map);
	
	/**
	 * appId前15位模糊查询stopFlag
	 * @param appId
	 * @return
	 */
	List<String> queryStopFlagByAppId(String appId);
	/**
	 * 查询分件表历史表的每个环节的最后操作人
	 * @param appId
	 * @param currNode
	 * @return
	 */
	String queryCurrOptUserByNode(Map<String,String> map);
	
	/**
	 * 查询分件表历史表的每个环节的操作人列表(不重复)
	 * @param appId
	 * @param currNode
	 * @return
	 */
	List<String> queryListOptUserByNode(Map<String,String> map);
	
	/**
	 * 查询用户权限
	 * @return
	 */
	List<String> queryListRoleCodeByUserCode(String userId);
}
