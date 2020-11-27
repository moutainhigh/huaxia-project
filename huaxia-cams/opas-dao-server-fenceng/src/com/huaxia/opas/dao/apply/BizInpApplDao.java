package com.huaxia.opas.dao.apply;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.input.BizInpAppl;
/**
 * 进件信息表
 * @author xiebinxu
 * 2017年3月1日
 */
public interface BizInpApplDao {
    
    int queryCount(Map record) throws CoreException;
    
    List queryList(Map record, int curNum, int pageNum) throws CoreException;
    
    List<BizInpAppl> queryBizInpApplListForExamine(Map map, int curNum, int pageNum) throws CoreException;
    
    List selectAppList(BizInpAppl biz, int curNum, int pageNum) throws CoreException;
    
    int selectAppCount(BizInpAppl biz) throws CoreException;
    
    int queryFinishedCount(Map<String, Object> map) throws CoreException;

	int queryToHighlevelCount(Map<String, Object> map) throws CoreException;

	int queryToFileCount(Map<String, Object> map) throws CoreException;

	int queryFinishedCount1(Map<String, Object> map) throws CoreException;

	int queryUnfinishedCount(Map<String, Object> map) throws CoreException;
	/**
	 *@Title:selectProcessIdByAppId
	 *@Description:通过appid获取工作流发起任务记录表的processid
	 *@param record
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月6日上午19:56:29
	 */
	public Map<String,Object> selectProcessIdByAppId(Map paramMap);
	
	//zhanglibo 征信  组长调阅  根据user_code 查询 DEL_STATUS = 0未完成
    int queryBizInpApplCountStatusZero(String userCode) throws CoreException;
    //根据user_code 查询DEL_STATUS = 2补件
  	public int queryBizInpApplCountStatusTwo(String userCode) throws CoreException;
  	//根据user_code 查询 DEL_STATUS = 3退回
  	public int queryBizInpApplCountStatusThree(String userCode) throws CoreException;
	//根据user_code 查询 DEL_STATUS = 4挂起
  	public int queryBizInpApplCountStatusFour(String userCode) throws CoreException;
  	//查询已完成件
  	public int queryBizInpApplCountStatusFinised(String userCode) throws CoreException;
	//根据当前操作人CurrOptUser  查询件
  	public List<BizInpAppl> queryBizInpApplByCurrOptUser(String currOptUser) throws CoreException;
  	
  	public int queryCountStatusAndUserCodeAnddelStatus(Map map) throws CoreException;
  	
  	//查询已完成件（已完成未归档）
  	public int queryFinisedApp(Map map) throws CoreException;
  	
  	public List<BizInpAppl> queryBizInpApplByCurrOptUserCheckup(String userCode)throws CoreException;
  	
  	public List<BizInpAppl> queryBizInpApplByCurrOptUserApprove(String userCode)throws CoreException;
  	/**
  	 *@Title:selectZxIndividualQueueDataCount
  	 *@Description:征信个人队列的查询数据条数 （未完成，待补件，退回）
  	 *@param paramMap
  	 *@return
  	 *@author: gaohui
  	 *@Date:2017年5月27日上午11:25:53
  	 */
  	public int selectZxIndividualQueueDataCount(Map paramMap);
  	/**
  	 *@Title:selectZxIndividualQueueDataList
  	 *@Description:征信个人队列的查询数据（未完成，待补件，退回）
  	 *@param paramMap
  	 *@param curNum
  	 *@param pageNum
  	 *@return
  	 *@author: gaohui
  	 *@Date:2017年5月27日上午11:34:17
  	 */
  	public List selectZxIndividualQueueDataList(Map paramMap, int curNum, int pageNum);
  	/**
  	 *@Title:selectBizInpApplZxListFinishedCount
  	 *@Description:获取征信已完成队列所需数据条数
  	 *@param paramMap
  	 *@return
  	 *@author: gaohui
  	 *@Date:2017年4月15日下午3:50:11
  	 */
	public int selectBizInpApplZxListFinishedCount(Map paramMap);
	/**
	 *@Title:selectBizInpApplZxListFinishedList
	 *@Description:获取征信已完成队列所需数据
	 *@param paramMap
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月15日下午4:32:36
	 */
	public List selectBizInpApplZxListFinishedList(Map paramMap, int curNum, int pageNum);
	/**
	 *@Title:selectAllotApplyCurrStatusByMap
	 *@Description:根据 appId和ydjFlag获取 申请件分配表数据(curr_status)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月17日下午4:54:42
	 */
	public Map selectAllotApplyCurrStatusByMap(Map paramMap);
	/**
	 *@Title:selectAllotApplyCurrStatusByMap
	 *@Description:根据 appId和ydjFlag和group获取 申请件分配表数据(curr_status)
	 *@param paramMap
	 *@return
	 *@author: daihao
	 *@Date:2020年8月20日下午4:54:42
	 */
	public Map selectAllotApplyCurrStatusByGroupMap(Map paramMap);
	/**
	 *@Title:insertCopyOpasAllotToAllotHis
	 *@Description:根据流水号 将此流水号在申请件分配表的信息插入到分件历史表
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年4月17日下午5:15:13
	 */
	public void insertCopyOpasAllotToAllotHis(Map paramMap);
	/**
	 *@Title:insertCopyOpasAllotToAllotHisByListAppId
	 *@Description:根据流水号前十五位   将此流水号在申请件分配表的信息插入到分件历史表
	 *@param paramMap 存放 所需的listAppId集合 
	 *@author: gaohui
	 *@Date:2017年5月25日下午8:42:03
	 */
	public void insertCopyOpasAllotToAllotHisByListAppId(Map paramMap);
	/**
	 *@Title:selectCreditDecisionResultByAppId
	 *@Description:根据appId 从OPAS_BZK_SYSRESULT_INFO表中获取征信 决策结果(CREDIT_DICISION_RESULT)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月17日下午6:33:58
	 */
	public Map selectCreditDecisionResultByAppId(Map paramMap);
	/**
	 *@Title:selectUserOrgOrgIdByUserUuid
	 *@Description:从 表opas_ap_user_org(用户组别关系表) 中 获取  org_id （机构Id）
	 *@param paraMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月17日下午7:01:30
	 */
	public Map selectUserOrgOrgIdByUserUuid(Map paramMap);
	/**
	 *@Title:selectApOrgOrgNoByOrgId
	 *@Description:根据orgId 查用户组管理[OPAS_AP_ORG]表 获取 ORG_NO（组代码）
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月17日下午7:47:27
	 */
	public Map selectApOrgOrgNoByOrgId(Map paramMap);
	/**
	 *@Title:updateAllotApplyAllotByAppId
	 *@Description:根据appId修改 申请件分配表相应数据
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年4月17日下午8:21:45
	 */
	public void updateAllotApplyAllotByAppId(Map paramMap);
	/**
	 *@Title:selectListAllotApplyZxRobByMap
	 *@Description:征信手动抢件 根据 currNode=02、currStatus=1 获取申请件分配表相应数据
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月18日上午11:40:31
	 */
	public List<Map> selectListAllotApplyZxRobByMap(Map paramMap);
	/**
  	 *@Title:selectBizInpApplZxListHangUpCount
  	 *@Description:获取征信已完成队列所需数据条数
  	 *@param paramMap
  	 *@return
  	 *@author: gaohui
  	 *@Date:2017年4月19日下午3:50:11
  	 */
	public int selectBizInpApplZxListHangUpCount(Map paramMap);
	/**
	 *@Title:selectBizInpApplZxListHangUpList
	 *@Description:获取征信已完成队列所需数据
	 *@param record
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月19日下午4:32:36
	 */
	public List selectBizInpApplZxListHangUpList(Map record, int curNum, int pageNum);
	
	/**
	 *@Title:selectListApUserByListUserId
	 *@Description:根据UserId 集合 查出 ApUser集合
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月20日下午3:20:09
	 */
	public List<Map<String,Object>> selectListApUserByListUserId(Map<String,Object> paramMap);
	/**
	 *@Title:selectApUserByUuIdUserCode
	 *@Description:根据uuid(user_id)或userCode获取user表的数据
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年6月24日下午2:33:51
	 */
	public Map<String,Object> selectApUserByUuIdUserCode(Map<String,Object> paramMap);

	int queryCountWc(Map record) throws CoreException;

	List queryListWc(Map record, int curNum, int pageNum) throws CoreException;
	/**
	 *@Title:updateZxApplyYdjHangUpByAppIds
	 *@Description:根据appId的list集合修改 挂起状态 curr_status
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年4月22日上午11:32:31
	 */
	public void updateZxApplyYdjHangUpByAppIds(Map paramMap);
	/**
	 *@Title:selectAllotApplyByMapLikeListAppId
	 *@Description:根据15流水号的list集合找到相应的套卡（非池中0、组中1、队列中6）
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年6月20日下午5:20:41
	 */
	public List<Map<String, Object>> selectAllotApplyByMapLikeListAppId(Map paramMap);

	/**
	 *@Description:征信电核环节易达金完成工作量统计
	 *@param map
	 *@author: susha
	 *@Date:2017年5月3日
	 */
	int queryYDJFinishedCount(Map<String, Object> map);
	int queryFicoYdjCount(Map<String, Object> map);
	int queryFicoBzkCount(Map<String, Object> map);
	/**
	 *@Description:征信电核环节易达金未完成工作量统计
	 *@param map
	 *@author: susha
	 *@Date:2017年5月3日
	 */
	int queryYDJUnfinishedCount(Map<String, Object> map);
	/**
	 *@Description:征信电核环节易达金提交至其他环节工作量统计
	 *@param map
	 *@author: susha
	 *@Date:2017年5月3日
	 */
	int queryYDJSub2Other(Map<String, Object> map);

	/**
	 *@Title:updateZxApplyFinishedTakeBackByListAppId
	 *@Description:征信 已完成队列 收回 所选的申请件 
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年4月24日下午3:47:30
	 */
	public void updateZxApplyFinishedTakeBackByListAppId(Map paramMap);

	int queryCountSp(Map record) throws CoreException;

	List queryListSp(Map record, int curNum, int pageNum) throws CoreException;
	/**
	 *@Title:updateAllotByMapLikeAppId
	 *@Description:通过 前十五位 appId 修改 申请件分配表状态
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年5月25日下午4:16:31
	 */
	public int updateAllotByMapLikeAppId(Map<String, Object> paramMap);
	/**
	 *@Title:selectAllotApplyByMapLikeAppId
	 *@Description:根据 前十五位appId和ydjFlag获取 申请件分配表数据(curr_status)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年5月26日下午1:39:37
	 */
	public List<Map> selectAllotApplyByMapLikeAppId(Map paramMap);
	/**
	 *@Title:selectAllotApplyByMapLikeCurrstatusAppId
	 *@Description:根据 前十五位appId和ydjFlag获取 池或组中的申请件分配表数据
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年6月15日下午8:17:11
	 */
	public List<Map>selectAllotApplyByMapLikeCurrstatusAppId(Map paramMap);
	/**
	 * 录入审查未完成队列显示
	 * @param map
	 * @return 
	 * @author: wangtao
	 */
	int queryCountForExamine(Map<String, Object> map);

	int queryGtCount(Map record) throws CoreException;

	List queryGtList(Map record, int curNum, int pageNum) throws CoreException;
	
	/**
	 *@Title:selectBizInpApplByUserCode
	 *@Description: 根据userCode 查询件  征信
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年6月27日上午午10:27:11
	 */
	List<BizInpAppl> selectBizInpApplByUserCode(Map<String, Object> paramMap);
	/**
	 *@Title:selectBizInpApplByUserCodeApprove
	 *@Description: 根据userCode 查询件  审批
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年6月28日下午13:55:20
	 */
	List<BizInpAppl> selectBizInpApplByUserCodeApprove(Map<String, Object> paramMap);
	/**
	 *@Title:selectBizInpApplByUserCodeApproveCompleted
	 *@Description: 根据userCode 查询件  审批已完成
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年6月28日下午16:20:10 
	 */
	List<BizInpAppl> selectBizInpApplByUserCodeApproveCompleted(Map<String, Object> paramMap);
	/**
	 *@Title:selectBizInpApplByUserCodeCheckup
	 *@Description: 根据userCode 查询件 审查
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年6月28日下午16:41:42
	 */
	List<BizInpAppl> selectBizInpApplByUserCodeCheckup(Map<String, Object> paramMap);

	List<BizInpAppl> selectBizInpApplApprove(Map<String, Object> dataMap);
	/**
	 *@Title:selectBizInpApplApproveCount
	 *@Description: 根据userCode 审批的count  挂起未完成待补件
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年7月11日上午午10:40:42
	 */
	int selectBizInpApplApproveCount(Map<String, Object> record);
	/**
	 *@Title:selectCountCheckup
	 *@Description: 根据userCode 录入审查的count  挂起未完成
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年7月11日上午午11:00:22
	 */
	int selectCountCheckup(Map<String, Object> map);
	/**
	 *@Title:selectZxMemerJobCount
	 *@Description: 根据userCode 征信count  挂起未完成退回补件
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年7月11日上午午11:20:26
	 */
	int selectZxMemerJobCount(Map<String, Object> paramMap);
	/**
	 *@queryYdjFlag
	 *@Description: 根据appid查询ydj_flag字段
	 *@param paramMap
	 *@return
	 *@author: 李鹏飞
	 *@Date:2017年7月12日下午14:47:26
	 */
	Map<String,Object> queryYdjFlag(Map appIdMap);
	/**
	 *@Title:selectZxMemerJobFinishedCount
	 *@Description: 根据userCode 征信count  已完成队列
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年7月11日上午午9:35:24
	 */
	int selectZxMemerJobFinishedCount(Map<String, Object> paramMap);
	/**
	 *@Title:selectApprovefinishedCount
	 *@Description: 根据userCode 审批count  已完成队列
	 *@param paramMap
	 *@return
	 *@author: 安东
	 *@Date:2017年7月11日上午午9:59:33
	 */
	int selectApprovefinishedCount(Map<String, Object> paramMap);
	/**
	 *@Title:selectExamineLibraryCheckMessage
	 *@Description:征信调查页面 获取审查库 校验信息 
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年8月2日下午2:35:57
	 */
	Map<String,String> selectExamineLibraryCheckMessage(Map map);
	/**
	 * 当前人未完成队列未完成件数
	 * @return
	 */
	int countUnFinishByCondition(Map map);
	/**
	 *@Title:selectApplyCurrentLinkCountByMap
	 *@Description:查询申请件在池中的条数 用于判断是否存在于当前环节
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年11月9日下午5:09:49
	 */
	int selectApplyCurrentLinkCountByMap(Map<String, Object> paramMap);
	/**
	 * 录入审查申请件获取
	 * @param conditionMap
	 * @return  汪涛
	 */
	Map selectAllotApplyCurrStatusByMapForExamine(Map<String, Object> conditionMap);
	/**
	 * 获取套卡标识
	 * @param String
	 * @return  wangdebin
	 */
	String selectMatchingCardFlag(String appId);

	List<String> queryQrcodeList(String appId);

	String queryQrcode(String appId);
}