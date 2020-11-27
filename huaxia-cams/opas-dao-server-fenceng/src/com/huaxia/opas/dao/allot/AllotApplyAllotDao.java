package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;

public interface AllotApplyAllotDao {
	int deleteByPrimaryKey(String appId);

	int insert(AllotApplyAllot record);

	int insertSelective(AllotApplyAllot record);

	AllotApplyAllot selectByPrimaryKey(String appId);

	int updateByPrimaryKeySelective(AllotApplyAllot record);

	int updateByPrimaryKey(AllotApplyAllot record);

	List<AllotApplyAllot> selectByUserId(String currOptUser);

	List<AllotApplyAllot> selectAllotByCondition(Map map);

	//zlb
	List<AllotApplyAllot> querySingleInfo(String appId) throws CoreException;

	List<AllotApplyAllot> queryStaffJobDetailByCurrNode(String currNode) throws CoreException;

	AllotApplyAllot selectZSOneByUserId(Map<String, String> map);

	AllotApplyAllot selectOneByUserId(Map<String, String> map);
	/**
	 * wangtao
	 * 录入审查未完成队列申请件获取以及抢件（更新申请件分配表状态）
	 * @param updateAllotMap
	 * @return
	 */
	int updateAllotForExamine(Map<String, Object> updateAllotMap);
	/**
	 * wangtao  
	 * 录入审查环节根据流水号前15位模糊获取申请件（有套卡一起获取）
	 * @param string
	 * @return
	 */
	List<AllotApplyAllot> selectAllotListByAppId(String appId);
	
	/**
	 * wangtao
	 * 审查录入环节抢组里的件
	 * @param conditionMap
	 * @return
	 */
	List<AllotApplyAllot> selectAllotForExamineByRob(Map<String, Object> conditionMap);

	List<AllotApplyAllot> getSpAllotByCondition(Map map);

	AllotApplyAllot selectAllotAndAppProdByAppId(String appId);
	
	int updateSpFlagByAppId(Map map);

	List<AllotApplyAllot> selectByAppId_15(String appId);

	Map<String,Object> selectDelStatusByAppId(String appId);

	String selectZSCurrentNumByUserId(Map<String, String> map);

	AllotApplyAllot selectZSNextOneByUserId(Map<String, String> map);

	/**
	* @author wangdebin
    * @version v1.1(初始v1.0)
    * @history 修改历史记录
    * * ------------------------------------------------
    * 修改组时查询是否有件未处理完
    *  -------------------------------------------------
	 */
	List<AllotApplyAllot> selectApplyByUserId(Map map);

	int updateSecDecisionFlag1(String appId);

	int updateSecDecisionFlag0(String appId);

	String selectSecDecisionFlag(String appId);
	
	/**
	 * 通过appid获取外国人公安查询结果
	 * @param appIdThd
	 * @return
	 */
	List<Map<String, String>> queryForeignCheckByAppId(String appId);
}