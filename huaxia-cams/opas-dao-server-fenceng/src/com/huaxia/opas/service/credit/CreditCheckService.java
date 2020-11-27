package com.huaxia.opas.service.credit;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.Candidate;
import com.huaxia.opas.domain.credit.CreditCheck;
import com.huaxia.opas.domain.input.BizInpApplC1;

/*******************************
 * @describe:征信提报、复核、调查、审批公用Service
 * @author：xiaoJianFeng
 * @date:2017-03-02
 ********************************/
public interface CreditCheckService {
	/*********************************
	 * @describe:征信调查复核分页查询
	 * @param paraMap
	 * @param begNum
	 *            开始条数
	 * @param pageNum
	 *            当前页数
	 * @return Map<String, Object>
	 * @author：xiaoJianFeng
	 * @date:2017-03-02
	 **********************************/
	Map<String, Object> selectCreditCheckPage(Map<String, Object> paraMap, int begNum, int pageNum);

	/**********************
	 * @describe: 根据申请编号查询
	 * @param paraMap
	 * @return CreditCheck
	 * @author：xiaoJianFeng
	 * @date:2017-03-02
	 ***********************/
	CreditCheck selectCreditCheckByApp_id(Map<String, String> paraMap);

	/***********************
	 * @describe:征信调查复核通过
	 * @param creditCheck
	 * @return int
	 * @author：xiaoJianFeng
	 * @date:2017-03-08
	 ***********************/
	int updateCreditCheck(CreditCheck creditCheck);

	/*********************************
	 * @describe:征信提报反欺诈分页查询
	 * @param paraMap
	 * @param begNum
	 *            开始条数
	 * @param pageNum
	 *            当前页数
	 * @return Map<String, Object>
	 * @author：xiaoJianFeng
	 * @date:2017-03-09
	 **********************************/
	Map<String, Object> selectCreditCheckCheatPage(Map<String, Object> paraMap, int begNum, int pageNum);

	/**********************
	 * @describe:提报反欺诈
	 * @param List<CreditCheck>
	 * @param CreditCheck
	 *            credit
	 * @return int
	 * @throws CoreException
	 * @throws Exception
	 * @author：xiaoJianFeng
	 * @date:2017-03-10
	 ***********************/
	void submitCheat(List<CreditCheck> lists, CreditCheck credit) throws CoreException, Exception;

	/**********************
	 * @describe:添加提报备注原因
	 * @param CreditCheck
	 * @return int
	 * @author：xiaoJianFeng
	 * @date:2017-03-10
	 ***********************/
	int insertReasonInfo(CreditCheck creditCheck);

	/***************************
	 * @describe:提报反欺诈原因码查询
	 * @param Map<String,
	 *            String> paraMap
	 * @return List<CreditCheck>
	 * @author：xiaoJianFeng
	 * @date:2017-03-10
	 ****************************/
	List<CreditCheck> selectMentionAntiFraud(Map<String, String> paraMap);

	/***************************
	 * @describe:添加征信提报欺诈结果信息表
	 * @param CreditCheck
	 *            creditCheck
	 * @return int
	 * @author：xiaoJianFeng
	 * @date:2017-03-10
	 ****************************/
	int insertOpasSubmitFraudInfo(CreditCheck creditCheck);

	/***************************
	 * @describe:征信提交欺诈复核
	 * @param CreditCheck
	 *            creditCheck
	 * @throws Exception
	 * @author：xiaoJianFeng
	 * @date:2017-03-10
	 ****************************/
	void cheatCheck(CreditCheck creditCheck, String type, String userUuid, Map map) throws CoreException, Exception;

	/*********************************
	 * @describe:征信欺诈调查分页查询
	 * @param CreditCheck
	 *            creditCheck
	 * @param begNum
	 *            开始条数
	 * @param pageNum
	 *            当前页数
	 * @return Map<String, Object>
	 * @author：xiaoJianFeng
	 * @date:2017-03-11
	 **********************************/
	Map<String, Object> selectCreditCheatInvestigationPage(Map<String, Object> paraMap, int begNum, int pageNum);

	/*********************************
	 * @describe:征信审批分页查询
	 * @param paraMap
	 * @param begNum
	 *            开始条数
	 * @param pageNum
	 *            当前页数
	 * @return CreditCheck creditCheck
	 * @author：xiaoJianFeng
	 * @date:2017-03-11
	 **********************************/
	Map<String, Object> selectCreditCheatApprovalPage(CreditCheck creditCheck, int begNum, int pageNum);

	/**********************
	 * @describe:征信欺诈调查原因码查询
	 * @return List<CreditCheck>
	 * @author：xiaoJianFeng
	 * @date:2017-03-11
	 ***********************/
	List<CreditCheck> selectOpasApDict();

	/**********************
	 * @describe:修改征信提报欺诈结果信息表
	 * @return List<CreditCheck>
	 * @author：xiaoJianFeng
	 * @date:2017-03-11
	 ***********************/
	void updateCheatInvestigation(CreditCheck creditCheck);

	/**********************
	 * @describe:修改申请件分配表部分字段
	 * @return List<CreditCheck>
	 * @author：xiaoJianFeng
	 * @date:2017-03-11
	 ***********************/
	void updateOpasAllotApplyAllot(CreditCheck creditCheck);

	/**********************
	 * @describe:根据app_id删除提报原因备注表信息
	 * @param: app_id申请件编号
	 * @author：xiaoJianFeng
	 * @date:2017-03-13
	 ***********************/
	void deleteOpasReasonInfoByAppID(String app_id);

	/**********************
	 * @describe:根据autoId删除征信提报欺诈结果信息表
	 * @param: autoId
	 *             征信提报欺诈结果信息表 ID
	 * @author：xiaoJianFeng
	 * @date:2017-03-13
	 ***********************/
	void deleteOpasSubmitfraudInfoByautoID(String autoId);

	/**********************
	 * @describe:根据autoId查询征信提报欺诈结果信息表
	 * @param: autoId
	 *             征信提报欺诈结果信息表 ID
	 * @author：xiaoJianFeng
	 * @date:2017-03-13
	 ***********************/
	List<CreditCheck> selectOpasOpasSubmitfraudInfoByautoID(String autoId);

	/**********************
	 * @param userCode
	 * @describe:反欺诈调查提交、保存、回退
	 * @param: CreditCheck
	 * @throws Exception
	 * @author：xiaoJianFeng
	 * @date:2017-03-13
	 ***********************/
	void cheatInvestigationHandle(CreditCheck creditCheck, String userCode, String userUuid)
			throws CoreException, Exception;

	/**********************
	 * @describe:添加 征信提报欺诈结果信息表数据
	 * @param: CreditCheck
	 * @param context
	 * @author：xiaoJianFeng
	 * @date:2017-03-13
	 ***********************/
	void insertOpasSubmitFraudInfoHis(CreditCheck creditCheck);

	/**********************
	 * @describe:复制 征信提报欺诈结果信息表 到历史表
	 * @param: CreditCheck
	 * @param context
	 * @throws Exception
	 * @author：xiaoJianFeng
	 * @date:2017-03-13
	 ***********************/
	void creditcheatExamineHandle(CreditCheck creditCheck, String uuid) throws CoreException, Exception;

	/********************************
	 * @describe:查询候选人列表
	 * @param candidate
	 * @param begNum
	 * @param pageNum
	 * @author：xiaoJianFeng
	 * @date:2017-03-15
	 *********************************/
	Map<String, Object> selectCandidate(Candidate candidate, int begNum, int pageNum);

	/********************************
	 * @describe:工作量统计
	 * @param paraMap
	 * @author：xiaoJianFeng
	 * @date:2017-03-19
	 *********************************/
	String workloadStatistics(Map<String, String> paraMap);

	/********************************
	 * @describe:查询提报原因
	 * @param appId
	 * @author：xiaoJianFeng
	 * @date:2017-03-30
	 *********************************/
	List<CreditCheck> selectReasonByAppId(String appId);

	/********************************
	 * @describe:申请件获取
	 * @param appId
	 * @author：susha
	 * @date:2017-04-17
	 *********************************/
	int getOne(Map<String, Object> map);

	/********************************
	 * @describe:手动抢件
	 * @param appId
	 * @author：susha
	 * @date:2017-04-17
	 *********************************/
	int getThree(Map<String, Object> map);

	/********************************
	 * @describe:提报反欺诈环节风险名单库自动维护
	 * @param dataMap
	 * @throws CoreException
	 * @author：susha
	 * @date:2017-05-12
	 *********************************/
	void addRiskList(Map<String, Object> dataMap, String crediter) throws CoreException;

	/********************************
	 * @describe:查询拥有复核员角色的用户列表
	 * @param crediter
	 * @throws CoreException
	 * @author：susha
	 * @date:2017-05-22
	 *********************************/
	String queryGroup(String crediter) throws CoreException;

	/********************************
	 * @describe:查询拥有欺诈调查审批角色的用户列表
	 * @param crediter
	 * @throws CoreException
	 * @author hang
	 *
	 *********************************/
	List findQzOperationPerson(Map paramMap, String userId) throws CoreException;

	/********************************
	 * @describe:推送
	 * @param crediter
	 * @throws CoreException
	 * @author hang
	 *
	 *********************************/
	CreditCheck selfTheOne(Map paramMap) throws CoreException;
	/**
	 *@param userCode 
	 * @describe:反欺诈调查派发协查
	  *@param: CreditCheck   
	 * @throws Exception 
	  *@author：liuheng
	  *@date:2019-08-22
	  ***********************/	
	 void cheatInvestigationCredit(CreditCheck creditCheck, String userCode,String userUuid) throws CoreException, Exception;
	 /**
	  * @param appId
	  * @describe 派发协查日期查询
	  * @author liuheng
	  * @date 2019-09-10
	  */
	 public List<Map<String, Object>> queryPfxcTime(String appId) throws CoreException;
	/**
	 * 智能语音复核队列
	 * 
	 * 
	 * 
	 */
	Map<String, Object> selectCreditSVoiceCheckPage(Map<String, Object> paraMap, int begNum, int pageNum);
}
