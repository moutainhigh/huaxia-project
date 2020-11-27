package com.huaxia.opas.dao.credit;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.Candidate;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.credit.CreditSVoice;
import com.huaxia.opas.domain.credit.OpasTelcheckResult;
import com.huaxia.opas.domain.input.BizInpAppl;
import com.huaxia.opas.domain.input.BizInpApplC1;
/*******************************
 *@describe:征信提报、复核、调查、审批公用接口
 *@author：xiaoJianFeng
 *@date:2017-03-02
 ********************************/
public interface CreditCheckDao {
	/*********************************
	 *@describe:征信调查复核分页查询
	 *@param paraMap
	 *@param begNum 开始条数
	 *@param pageNum 当前页数
	 *@return List<CreditCheck>
	 *@author：xiaoJianFeng
	 *@date:2017-03-02
	 **********************************/
	List<CreditCheck> selectCreditCheckPage(Map<String, Object> paraMap, int begNum, int pageNum);
	 /**********************
	 *@describe:征信调查复核总条数
	  *@param paraMap
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-02
	  ***********************/
	 String selectCreditCheckCount(Map<String, Object> paraMap);
	 /**********************
	 *@describe:征信调查复核总条数
	  *@param paraMap
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-02
	  ***********************/
	 CreditCheck selectCreditCheckByApp_id(Map<String, String> paraMap);
	 /***********************
	  *@describe:征信调查复核通过
	  *@param creditCheck
	  *@return int
	  *@author：xiaoJianFeng
	  *@date:2017-03-08
	  ***********************/
	 int updateCreditCheck(CreditCheck creditCheck);
	 /***********************
	  *@describe:征信调查复核通过      清空操作人 组 队列 操作时间 等相关信息
	  *@param creditCheck
	  *@return int
	  *@author：susha
	  *@date:2017-06-01
	  ***********************/
	 int updateCreditCheck1(CreditCheck creditCheck);
	/*********************************
	 *@describe:征信提报反欺诈分页查询
	 *@param paraMap
	 *@param begNum 开始条数
	 *@param pageNum 当前页数
	 *@return List<CreditCheck>
	 *@author：xiaoJianFeng
	 *@date:2017-03-09
	 **********************************/
	 List<CreditCheck> selectCreditCheckCheatPage(Map<String, Object> paraMap, int begNum, int pageNum);
	 /**********************
	 *@describe:征信提报反欺诈总条数
	  *@param paraMap
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-02
	  ***********************/
	 String selectCreditCheckCheatCount(Map<String, Object> paraMap);
	 /**********************
	 *@describe:添加提报备注原因
	  *@param CreditCheck
	  *@return int
	  *@author：xiaoJianFeng
	  *@date:2017-03-10
	  ***********************/
	int insertReasonInfo(CreditCheck creditCheck); 
	 /***************************
	 *@describe:提报反欺诈原因码查询
	  *@param Map<String, String> paraMap
	  *@return  List<CreditCheck>
	  *@author：xiaoJianFeng
	  *@date:2017-03-10
	  ****************************/
	 List<CreditCheck> selectMentionAntiFraud(Map<String, String> paraMap);
	 /***************************
	 *@describe:添加征信提报欺诈结果信息表
	  *@param CreditCheck creditCheck
	  *@return int
	  *@author：xiaoJianFeng
	  *@date:2017-03-10
	  ****************************/
	 int insertOpasSubmitFraudInfo(CreditCheck creditCheck);
	/*********************************
	 *@describe:征信欺诈调查分页查询
	 *@param CreditCheck creditCheck
	 *@param begNum 开始条数
	 *@param pageNum 当前页数
	 *@return List<CreditCheck>
	 *@author：xiaoJianFeng
	 *@date:2017-03-11
	 **********************************/
	 List<CreditCheck> selectCreditCheatInvestigationPage(Map<String, Object> paraMap, int begNum, int pageNum);
	/*********************************
	 *@describe:征信审批分页查询
	 *@param paraMap
	 *@param begNum 开始条数
	 *@param pageNum 当前页数
	 *@return  List<CreditCheck>
	 *@author：xiaoJianFeng
	 *@date:2017-03-11
	 **********************************/
	 List<CreditCheck> selectCreditCheatApprovalPage(CreditCheck creditCheck, int begNum, int pageNum);
	 /**********************
	 *@describe:征信欺诈调查总条数
	  *@param CreditCheck creditCheck
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-11
	  ***********************/
	 String selectCreditCheatInvestigationCount(Map<String, Object> paraMap);
	 /**********************
	 *@describe:信审批列表总条数
	  *@param CreditCheck creditCheck
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-11
	  ***********************/
	 String selectCreditCheatApprovalCount(CreditCheck creditCheck);
	 /**********************
	 *@describe:征信欺诈调查原因码查询
	  *@return List<CreditCheck>
	  *@author：xiaoJianFeng
	  *@date:2017-03-11
	  ***********************/ 
	 List<CreditCheck> selectOpasApDict();
	 /**********************
	 *@describe:修改征信提报欺诈结果信息表
	  *@return List<CreditCheck>
	  *@author：xiaoJianFeng
	  *@date:2017-03-11
	  ***********************/ 
	 void updateCheatInvestigation(CreditCheck creditCheck);
	 /**********************
	 *@describe:修改申请件分配表部分字段
	  *@author：xiaoJianFeng
	  *@date:2017-03-11
	  ***********************/ 
	 void updateOpasAllotApplyAllot(CreditCheck creditCheck);
	 /**********************
	 *@describe:修改申请件分配表部分字段
	  *@author：xiaoJianFeng
	  *@date:2017-03-11
	  ***********************/ 
	 void updateOpasAllotApplyAllotToBack(CreditCheck creditCheck);
	 /**********************
	 *@describe:根据app_id删除提报原因备注表信息
	  *@param: app_id申请件编号 
	  *@author：xiaoJianFeng
	  *@date:2017-03-13
	  ***********************/ 
	 void deleteOpasReasonInfoByAppID(String app_id);
	 /**********************
	 *@describe:根据autoId删除征信提报欺诈结果信息表
	  *@param: autoId  征信提报欺诈结果信息表 ID
	  *@author：xiaoJianFeng
	  *@date:2017-03-13
	  ***********************/	 
	 void deleteOpasSubmitfraudInfoByautoID(String autoId);
	 /**********************
	 *@describe:根据autoId查询征信提报欺诈结果信息表
	  *@param: autoId  征信提报欺诈结果信息表 ID
	  *@author：xiaoJianFeng
	  *@date:2017-03-13
	  ***********************/	
	 List<CreditCheck> selectOpasOpasSubmitfraudInfoByautoID(String autoId);
	 /**********************
	 *@describe:添加 征信提报欺诈结果信息表数据
	  *@param: CreditCheck   
	  *@param  context
	  *@author：xiaoJianFeng
	  *@date:2017-03-13
	  ***********************/	
	 void insertOpasSubmitFraudInfoHis(CreditCheck creditCheck);
	 /**********************
	 *@describe:复制 征信提报欺诈结果信息表 到历史表
	  *@param: CreditCheck   
	  *@param  context
	  *@author：xiaoJianFeng
	  *@date:2017-03-13
	  ***********************/	
	void copyOpasSubmitFraudInfoToHis(Map<String, String> paraMap);
	/********************************
	 *@describe:查询候选人列表
	 *@param candidate
	 *@param begNum
	 *@param pageNum
	 *@author：xiaoJianFeng
	 *@date:2017-03-15
	 *********************************/
	List<CreditCheck> selectCandidate(Candidate candidate, int begNum, int pageNum);
	/********************************
	 *@describe:查询候选人列表条数
	 *@param candidate
	 *@author：xiaoJianFeng
	 *@date:2017-03-15
	 *********************************/
	 String selectCandidateCount(Candidate candidate);
	/********************************
	 *@describe:根据当前操作人 查询
	 *@param paraMap
	 *@author：xiaoJianFeng
	 *@date:2017-03-19
	 *********************************/
	 String selectOpasSubmitFraudInfoByOperationDate(Map<String, String> paraMap);
	 /********************************
	 *@describe:根据当前操作人 查询统计
	 *@param paraMap
	 *@author：李航
	 *********************************/
	 String selectWorkCountInvestigation(Map<String, String> paraMap);
	/********************************
	 *@describe:复制申请件分配表
	 *@param app_id
	 *@author：xiaoJianFeng 改 gaohui
	 *@date:2017-03-27 、 2017-4-10
	 *********************************/
	 void insertCopyOpasAllotApplyAllot(String app_id);
	/********************************
	 *@describe:根据申请件号查询原因吗
	 *@param appId
	 *@author：xiaoJianFeng
	 *@date:2017-03-30
	 *********************************/
	 List<CreditCheck> selectReasonByAppId(String appId);
	/********************************
	 *@describe:修改提报原因码
	 *@param creditCheck
	 *@author：xiaoJianFeng
	 *@date:2017-03-30
	 *********************************/
	 void updateReasonByAppId(CreditCheck creditCheck);
	/********************************
	 *@describe:一次性插入多条提报原因码
	 *@param creditCheck
	 *@author：xiaoJianFeng
	 *@date:2017-03-30
	 *********************************/	 
	 void insertReasonList(List<CreditCheck> creditChecks);
	/********************************
	 *@describe:根据Id删除提报原因码
	 *@author：xiaoJianFeng
	 *@date:2017-03-30
	 *********************************/	
	 void deleteReasonById(String reasonID);
	/********************************
	 *@describe:添加征信电核结果信息表
	 *@author：xiaoJianFeng
	 *@date:2017-03-30
	 *********************************/
	 void insertOpasTelcheckResult(OpasTelcheckResult telcheckResult);
	/********************************
	 *@describe:提报反欺诈申请件获取
	 *@author：susha
	 *@date:2017-04-17
	 *********************************/
	int updateOne(Map<String, Object> map) throws CoreException;
	/********************************
	 *@describe:提报反欺诈手动抢件
	 *@author：susha
	 *@date:2017-04-17
	 *********************************/
	int updateThree(Map<String, Object> map) throws CoreException;
	/********************************
	 *@describe:查询进件信息表信息
	 *@author：susha
	 *@date:2017-05-15
	 *********************************/
	BizInpApplC1 queryBizInpApplC1ByAppId(String appId);
	/********************************
	 *@describe:根据当前用户查询其所在组
	 *@author：susha
	 *@date:2017-05-22
	 *********************************/
	String queryGroup(String crediter);
	/********************************
	 *@describe:根据当前用户查询其所在组的名字
	 *@author：hang
	 *@date:2017-07-26
	 *********************************/
	String queryGroupName(String crediter);
	/********************************
	 *@describe:向申请件分配表中插入数据
	 *@author：susha
	 *@date:2017-05-26
	 *********************************/
	int insertOpasAllotApplyAllotHis(CreditCheck creditCheck);
	/********************************
	 *@describe:根据appId从欺诈结果信息表中查询征信提报员
	 *@author：susha
	 *@date:2017-05-26
	 *********************************/
	String querySubmitFraud(String appId);
	 /**********************
	  *@describe:修改申请件分配表部分字段
	  *@author：susha
	  *@date:2017-05-26
	  ***********************/ 
	int updateOpasAllotApplyAllot1(CreditCheck creditCheck);
	 /**********************
	  *@describe:根据appId查询上一操作人
	  *@author：susha
	  *@date:2017-05-26
	  ***********************/
	String queryAllotApplyAllotByAppId(String check_number);
	String queryCurrOptUserByAppId(String check_number);
	void insertCopyOpasAllotToAllotHis(CreditCheck creditCheck);
	/**
	 * 查询组id
	 * @param userId
	 * @author hang
	 * @return
	 */
	String selectOrgIdByUserId(String userId);
	/**
	 * 根据orgId查询当前组所有具有的审批权限的userId
	 * @param orgId
	 * @return
	 */
	List selectListApUserByOrgId(String orgId);
	/**
	 * 根据userID查询name和code
	 * @param paramMaplistUserId
	 * @return
	 */
	List<Map<String, Object>> selectListApUserByListUserId(
			Map<String, Object> paramMaplistUserId);
	
	/**
	 * 推送查询当前第一条数据
	 * @param paramMap
	 * @return
	 */
	CreditCheck selfTheOne(Map paramMap);
	/**
	 * 更新欺诈表
	 * @param paramMap
	 * @return
	 */
	void updateOpasSubmitFraudInfo(CreditCheck creditCheck);
	/**********************
	 *@describe:根据autoId查询征信提报欺诈结果信息表
	  *@param: autoId  征信提报欺诈结果信息表 ID
	  *@author：xiaoJianFeng
	  *@date:2017-03-13
	  ***********************/	
	 List<CreditCheck> selectOpasOpasSubmitfraudInfoByAppID(String appId);
	 /**
	  * 根据appId查询当前节点
	  * @param appId
	  * @return
	  */
	 String queryCurrNodeByAppId(String appId);
	 /**
	  * 根据appId查询欺诈当前节点
	  * @param appId
	  * @return
	  */
	 String queryqzCurrNodeByAppId(String appId);
	 
	 /**
	  * 判断是否需要更新
	  * @param appId
	  * @return
	  */
	 int countOpasSubmitfraudInfoByAppID(String appId);
	 /**
	  * 判断是否需要更新
	  * @param appId
	  * @return
	  */
	 String queryInvestMemo(String appId);
	 /**
	  * 判断是否当前队列
	  * @param appId
	  * @return
	  */
	 String queryqzVdelStatusByAppId(String appId);
	 String querySubmitTypeByAppId(String appId);
	 /**
	  * 查询欺诈原因码
	  * @param appId
	  * @return
	  */
	 List queryReasonCodeByAppId(String appId);
	 /**
	  * 查询组名
	  * @param OrgNo
	  * @return
	  */
	 String queryOrgNameByAppId(String OrgNo);
	 /**
	  * 回退更新备注为空
	  * @param appId
	  */
	 void updateOpasAllotApplySubmitMemo(String appId);

	 /**
	  * 查询预审分件表有没有数据
	  * @param appId
	  * @return
	  */
	 public List<CreditCheck> selectysallotapplyallotID(String appId);
	 /**
	  * 预审分件表插入数据
	  * @param appId
	  * @return
	  */
	 int insertysallotapplyallotID(CreditCheck creditCheck);
	 /**
		 * 更新预审分件表
		 * @param creditCheck
		 * @return
		 */
	 void updateYsAllotApplyAllot(CreditCheck creditCheck);
	 /**
	  * 查询派发协查时间
	  * @param appId
	  */
	 public List<Map<String, Object>> queryPfxcTime(String appId);

	 /**
	  * 智能语音查询当前页
	  * 
	  * 
	  */
	 List<CreditSVoice> selectCreditSVoiceCheckPage(Map<String, Object> paraMap, int begNum, int pageNum);
	 /**********************
	 *@describe:征信调查复核总条数
	  *@param paraMap
	  *@return String
	  *@author：xiaoJianFeng
	  *@date:2017-03-02
	  ***********************/
	 String selectCreditSVoiceCount(Map<String, Object> paraMap);
	 
	 /**********************
	 *@describe:查看智能语音服务是否已经发起
	  *@param appId
	  *@return Integer
	  *@author：
	  *@date:2020-08-20
	  ***********************/
	 Integer selectCallSVoiceCount(String appId);
}
