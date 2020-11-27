package com.huaxia.opas.service.apply;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.OpasTelcheckResult;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.sysparm.SubReport;
import com.huaxia.opas.domain.system.ApOrg;
/**
 * 进件信息表
 * @author xiebinxu
 * 2017年3月1日
 */
public interface BizInpApplService {
    
    int queryCount(Map record) throws CoreException;
    
    List queryList(Map record, int curNum, int pageNum) throws CoreException;
    
    AllotApplyAllot selectByPrimaryKey(String appId);
	
	int updateByPrimaryKey(AllotApplyAllot record);
	
	List<AllotApplyAllot> selectAllotByCondition(Map map);
	
	int countUnFinishByCondition(Map map);  
	
	List<ApOrg> queryUserOrgs(String userId);
    /**
    *@Title:updateByPrimaryKeySelective
    *@Description:修改AllotApplyAllot对象中 传来的数值不为空的字段
    *@param record
    *@return
    *@author: gaohui
    *@Date:2017年3月31日上午11:11:20
     */
	public void updateByPrimaryKeySelective(AllotApplyAllot record);
	/**************************************************
	 *@describe:征信页面提报
	 *@param creditCheck 
	 *@param  subReport
	 *@author xiaoJianFeng  2017。5.17 gaohui 改
	 *@date:2017-03-28
	 ***************************************************/
	public void updateCreditSubmitNewspaper(CreditCheck creditCheck,SubReport subReport);
	/**************************************************
	 *@describe:征信提交
	 *@param updateCreditSubmit 
	 *@param  telcheckResult 
	 *@author xiaoJianFeng 改 gaohui
	 *@date:2017-03-31 、 2017-4-10
	 ***************************************************/
	void updateCreditSubmit(CreditCheck creditCheck,OpasTelcheckResult telcheckResult);
	/**
	 * @describe:
	 * @param map
	 * @return
	 * @throws CoreException
	 * @author susha
	 * @date:2017-04-02
	 */
	int queryFinishedCount(Map<String, Object> map) throws CoreException;
	
	/**
	 * @describe:
	 * @param map
	 * @return
	 * @throws CoreException
	 * @author susha
	 * @date:2017-04-02
	 */
	int queryToHighlevelCount(Map<String, Object> map) throws CoreException;
	/**
	 * @describe:
	 * @param map
	 * @return
	 * @throws CoreException
	 * @author susha
	 * @date:2017-04-02
	 */
	int queryToFileCount(Map<String, Object> map) throws CoreException;
	/**
	 * @describe:
	 * @param map
	 * @return
	 * @throws CoreException
	 * @author susha
	 * @date:2017-04-02
	 */
	int queryFinishedCount1(Map<String, Object> map) throws CoreException;
	/**
	 * @describe:
	 * @param map
	 * @return
	 * @throws CoreException
	 * @author susha
	 * @date:2017-04-02
	 */
	int queryUnfinishedCount(Map<String, Object> map) throws CoreException;
	 /**
	  *@Title:queryProcessIdByAppId
	  *@Description:通过appid获取工作流发起任务记录表的processid
	  *@param record
	  *@return
	  *@author: gaohui
	  *@Date:2017年4月6日上午19:54:24
	  */
	public Map<String,Object> queryProcessIdByAppId(Map paramMap);
	/**
	 *@Title:findZxIndividualQueueDataCount
	 *@Description:征信个人队列的查询数据条数 （未完成，待补件，退回）
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年5月27日上午11:23:45
	 */
	public int findZxIndividualQueueDataCount(Map paramMap);
	/**
	 *@Title:findZxIndividualQueueDataList
	 *@Description:征信个人队列的查询数据 （未完成，待补件，退回）
	 *@param record
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年5月27日上午11:31:57
	 */
	public List findZxIndividualQueueDataList(Map record, int curNum, int pageNum);
	/**
	 *@Title:findBizInpApplZxListFinishedCount
	 *@Description:获取征信已完成队列所需数据的条数
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月15日下午3:46:48
	 */
	public int findBizInpApplZxListFinishedCount(Map paramMap);
	/**
	 *@Title:findBizInpApplZxListFinishedList
	 *@Description:获取征信已完成队列所需数据
	 *@param paramMap
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月15日下午4:26:24
	 */
	public List findBizInpApplZxListFinishedList(Map paramMap, int curNum, int pageNum);
	/**
	 *@Title:updateZxApplyGainBzk
	 *@Description:征信标准卡 获取申请件方法（修改相关列表内容）
	 *@param paraMap
	 *@author: gaohui
	 *@Date:2017年4月17日下午4:24:47
	 */
	public Map<String, Object> updateZxApplyGainBzk(Map<String,Object> paraMap);
	/**
	 *@Title:updateZxApplyGainYdj
	 *@Description:征信易达金获取申请件方法（修改相关列表内容）
	 *@param paraMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月18日上午10:41:19
	 */
	public Map<String, Object> updateZxApplyGainYdj(Map<String,Object> paraMap);
	/**
	 *@Title:updateZxApplyRobBzkYdj
	 *@Description:征信标准卡  易达金  手动抢件方法（修改相关列表内容）
	 *@param paraMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月18日上午11:33:10
	 */
	public Map<String, Object> updateZxApplyRobBzkYdj(Map<String,Object> paraMap);
	/**
	 *@Title:findBizInpApplZxListHangUpCount
	 *@Description:获取征信挂起队列所需数据的条数
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月19日下午3:46:48
	 */
	public int findBizInpApplZxListHangUpCount(Map paramMap);
	/**
	 *@Title:findBizInpApplZxListHangUpList
	 *@Description:获取征信挂起队列所需数据
	 *@param record
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月19日下午4:26:24
	 */
	public List findBizInpApplZxListHangUpList(Map record, int curNum, int pageNum);
	/**
	 *@Title:findZxExamineOperationPerson
	 *@Description:征信  获取 特定审批人
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月20日上午10:55:49
	 */
	public List<Map<String,Object>> findZxExamineOperationPerson(Map<String,Object> paramMap);

	int queryCountWc(Map record) throws CoreException;

	List queryListWc(Map record, int curNum, int pageNum) throws CoreException;
	/**
	 *@Title:updateZxApplyYdjHangUp
	 *@Description:根据appId的list集合 修改 挂起状态
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年4月22日上午11:29:49
	 */
	public void updateZxApplyYdjHangUp(Map paramMap);
	/**
	 *@Title:updateZxApplyYdjHangUpCancle
	 *@Description:根据appId的list集合 将 挂起状态变为解挂 
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年5月16日上午11:07:22
	 */
	public void updateZxApplyYdjHangUpCancle(Map paramMap);
	/**
	 *@Title:updateCreditSubmitZx
	 *@Description:征信提交按钮 调流
	 *@param creditCheck
	 *@param telcheckResult
	 *@param streamMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月22日下午7:53:52
	 */
	public Map<String,Object> updateCreditSubmitZx(CreditCheck creditCheck,OpasTelcheckResult telcheckResult,Map<String,Object> streamMap);

	/**
	 *@Description:征信电核环节易达金完成工作量统计
	 *@param map
	 *@author: susha
	 *@Date:2017年5月3日
	 */
	int queryYDJFinishedCount(Map<String, Object> map);
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
	 *@Title:updateZxApplyFinishedTakeBack
	 *@Description:征信 已完成队列 收回申请件功能 
	 *@param paramMap
	 *@author: gaohui
	 *@Date:2017年4月24日下午3:45:22
	 */
	public Map<String,Object> updateZxApplyFinishedTakeBack(Map paramMap);
	
	/**
	 * @author wangtao
	 * @Description:录入审查未完成队列的显示
	 * @param map
	 * @param curNum
	 * @param pageNum
	 * @return
	 */
	int queryCountForExamine(Map<String, Object> map) throws CoreException;
	List<BizInpAppl> queryBizInpApplListForExamine(Map map, int curNum, int pageNum) throws CoreException;

	int queryCountSp(Map record) throws CoreException;

	List queryListSp(Map record, int curNum, int pageNum) throws CoreException;
	public String lineNewUrlClient(Map<String, Object> streamMap) throws Exception;

	void updateListAllotApplyAllot(List<AllotApplyAllot> list, String userId,String flag) throws Exception;
	/**
	 * @author wangtao
	 * @Description:录入审查未完成队列根据AppId获取申请件
	 * @param conditionMap
	 * @return
	 */
	Map<String,Object> selectAllotByConditionByAppId(Map<String, Object> conditionMap);
	/**
	 * @author wangtao
	 * @Description:录入审查未完成队列抢件功能
	 * @param conditionMap
	 * @return
	 * @throws CoreException 
	 * @throws Exception 
	 */
	Map<String, Object> updateLrApplyForExamine(Map<String, Object> conditionMap) throws  Exception;

	List<AllotApplyAllot> getSpAllotByCondition(Map map);

	int queryGtCount(Map record) throws CoreException;

	List queryGtList(Map record, int curNum, int pageNum) throws CoreException;

	//查询ydjflag字段
	Map<String,Object> queryYdjFlag(Map appIdMap);

	Map<String, Object> selectApUserByUuIdUserCode(Map<String, Object> userMap);

	List<AllotApplyAllot> selectByAppId_15(String appId);
	/**
	 *@Title:findExamineLibraryCheckMessage
	 *@Description:征信调查页面 获取审查库 校验信息 
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年8月2日下午2:34:28
	 */
	Map<String,String> findExamineLibraryCheckMessage(Map map);
	/**
	 * @author: wangtao
	 * @param 当前操作人
	 * @return 当前操作人未完成队列申请件数量
	 */
//	int selectCountByCurrOptUserForExamine(String currOptUser);
	
	public Map<String,Object> addExamineToTopbpm(Map<String, Object> paramMap) throws Exception;

	Map<String, Object> queryQrcodeList(Map<String, Object> map);
	
	/**
	 *@Title:updateCreditSubmitZx
	 *@Description:征信件进入智能语音队列
	 *@param creditCheck
	 *@param telcheckResult
	 *@param streamMap
	 *@return
	 *@author: 
	 *@Date:
	 */
	public Map<String,Object> updateApplyZxToSVoice(CreditCheck creditCheck,Map<String,Object> streamMap);
	/**
	 * 
	 *@Desc:智能语音复核队列退回到征信
	 *@param processFlag 1.智能语音复核队列退回征信    2.智能语音回调审批退回征信 
	 *@return
	 *@author: 
	 *@Date: 
	 */
	public void updateApplySVoiceToZx(AllotApplyAllot allot, AllotApplyAllotHis allotHis,Map<String, Object> resMap, String processFlag) throws Exception;
	/**
	 * 
	 * @Desc:查询智能语音申请件的结果
	 * 
	 * 
	 */
	public boolean querySVoiceResult(String appId);
	/**
	 * 
	 * @Desc:智能语音复核提交
	 * 
	 */
	public Map<String, Object> updateApplySVoiceToAppro(CreditCheck creditCheck,Map<String, Object> streamMap);

}