package com.huaxia.opas.service.workflow;

import java.util.Map;

import com.huaxia.opas.domain.workflow.Workflow;
import com.huaxia.opas.domain.workflow.WorkflowCallback;


public interface WorkflowQuService {

	Map<String, Object> selectWorkflow(Workflow workflow);

//	Map<String, Object> queryTopbpmExceSolveAppid(WorkflowCallback workflowCallback);
	Map<String, Object> queryTopbpmExceSolveAppid(Map<String, String> paramMap,int curNum, int pageNum);

	void insertTopbpmTmpExecutionAndUpdateStatus(String appId);

	Map<String, Object> queryWeiguidangAppid(Map<String, String> paramMap, int curNum, int pageNum);

//	Map<String, Object> queryTopbpmExceSolveArtiAppid(Map<String, String> paramMap, int curNum, int pageNum);

	void solveExceptionSingleOrBatch(String appId);

	Map<String, Object> queryTopbpmExceSolveAutoAppid(Map<String, String> paramMap, int curNum, int pageNum);

	int updateErrorExceptionStatus();

	Map<String, Object> queryTopbpmTmpExecutionAppid(Map<String, String> paramMap, int curNum, int pageNum);

	Map<String, Object> queryTopbpmTmpExecutionAppidDetail(Map<String, String> paramMap);

	void updateTopbpmTmpExecutionDetail(Map<String, String> paramMap);

	void deleteTopbpmTmpExecutionDetail(Map<String, String> paramMap);

	void updateAppointErrorExceptionStatus(Map<String,String> paramMap);

	Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap);

	void insertTmpSingle(Map<String, String> paramMap);

	Map<String, Object> queryTopbpmProcessByAppid(Map <String,String> paramMap, int curNum, int pageNum);

	void updateAppointErrorProcessStatus(Map<String, String> paramMap);

	Map<String, Object> queryActivityInfo(Map<String, String> paramMap, int curNum, int pageNum);

	Map<String, Object> queryProcessvariableInfo(Map<String, String> paramMap, int curNum, int pageNum);

	Map<String, Object> queryProcessvariable_tInfo(Map<String, String> paramMap, int curNum, int pageNum);

	Map<String, Object> queryProcess_tDetail(Map<String, String> paramMap, int curNum, int pageNum);

	void updateProcessvariableInfo(WorkflowCallback workflowCallback);

	void updateProcessvariable_tInfo(WorkflowCallback workflowCallback);

	void updateProcess_tInfo(WorkflowCallback workflowCallback);

	void updateTierAppidStatus(Map<String,String> paramMap);

	void updateWeiguidangAppidStatus(Map<String, String> paramMap);
}
