package com.huaxia.opas.dao.checking;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.checking.QualityChecking;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.domain.system.ApUser;

public interface QualityCheckingDao {

	int queryOperatorCount(Map<String,Object> map);

	/**
	 * 质检查询条件操作员队列展示
	 * 
	 * @param operator
	 *            选择的是操作员/征信员/授信员/征审合一员
	 * @return
	 */
	List<ApUser> queryOperatorList(Map<String,Object> map, int curNum, int pageNum);

	int queryQualityCheckingCount(Map<String, Object> dataMap);

	/**
	 * 根据搜索条件展示质检列表
	 * 
	 * @param dataMap
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	List<QualityChecking> queryQualityCheckingList(Map<String, Object> dataMap, int curNum, int pageNum);

	/**
	 * 保存质检记录
	 * @param qualityChecking
	 * @return
	 */
	int saveQualityChecking(QualityChecking qualityChecking);
	
	/**
	 * 根据appId查询质检表里是否有这条记录
	 * @param appId
	 * @return
	 */
	int queryQualityCheckingCountByAppId(String appId);
	
	/**
	 * 更新质检表记录
	 * @param qualityChecking
	 * @return 不用了。质检记录只插入，不更新
	 */
//	int updateQualityChecking(QualityChecking qualityChecking);

	/**
	 * 根据appId查询质检结果
	 * @param appId
	 * @return
	 */
	QualityChecking selectQualityChecking(String appId);

	/**
	 * 根据当前登录的用户获取其所属的组
	 * @param userUuid
	 * @return
	 */
	String queryUserOrgNo(String userUuid);

	/**
	 * 根据appId查询质检状态
	 * @param appId
	 * @return
	 */
	String queryStopFlag(String appId);

	/**
	 * 根据日期查询该时间内的质检的件
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
	 * 处于叫停的件再次质检不更改叫停状态
	 * @param qualityChecking
	 * @return 不用 了，质检记录只插入不更新
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
	 * 根据appId去查询申请件分配表的一些状态
	 * @param appId
	 * @return
	 */
	Map<String, Object> querySomeFlagByAppId(String appId);

	String queryUserUuid(String appId);

	List<String> queryOperatorAllList(Map<String, Object> map);
	
	List<String> selectStopFlagByAppId(String appId);

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
