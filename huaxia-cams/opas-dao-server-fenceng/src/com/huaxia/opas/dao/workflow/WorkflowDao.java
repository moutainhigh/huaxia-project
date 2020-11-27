package com.huaxia.opas.dao.workflow;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.workflow.Workflow;
import com.huaxia.opas.domain.workflow.WorkflowCallback;

public interface WorkflowDao {//jpc0522

	int selectCount(Workflow workflow);
	
	List<Workflow> selectWorkflow(Workflow workflow);

//	int queryTopbpmExceSolveAppidCount(WorkflowCallback workflowCallback);
//
//	List<WorkflowCallback> queryTopbpmExceSolveAppid(WorkflowCallback workflowCallback);
	int queryTopbpmExceSolveAppidCount(Map<String, String> paramMap);
	
	List<WorkflowCallback> queryTopbpmExceSolveAppid(Map<String, String> paramMap,int curNum, int pageNum);

	int insertTopbpmTmpExecution(String appId);

	int updateTopbpmExceSolve(String appId);

	int queryWeiguidangAppidCount(Map<String, String> paramMap);

	List<WorkflowCallback> queryWeiguidangAppid(Map<String, String> paramMap, int curNum, int pageNum);

//	int queryTopbpmExceSolveArtiAppidCount(Map<String, String> paramMap);
//
//	List<WorkflowCallback> queryTopbpmExceSolveArtiAppid(Map<String, String> paramMap, int curNum, int pageNum);

	int insertExceptionTopbpmTmpExecution(String appId);

	int updateTopbpmException(String appId);

	int queryTopbpmExceSolveAutoAppidCount(Map<String, String> paramMap);

	List<WorkflowCallback> queryTopbpmExceSolveAutoAppid(Map<String, String> paramMap, int curNum, int pageNum);

	int updateErrorExceptionStatus();

	List<WorkflowCallback> queryTopbpmTmpExecutionAppid(Map<String, String> paramMap, int curNum, int pageNum);

	int queryTopbpmTmpExecutionAppidCount(Map<String, String> paramMap);

	Map<String, Object> queryTopbpmTmpExecutionAppidDetail(Map<String, String> paramMap);

	void updateTopbpmTmpExecutionDetail(Map<String, String> paramMap);

	void deleteTopbpmTmpExecutionDetail(Map<String, String> paramMap);

	void updateAppointErrorExceptionStatus(Map<String,String> paramMap);

	Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap);

	void insertTmpSingle(Map<String, String> paramMap);
	
	List<WorkflowCallback> queryTopbpmProcessByAppid(Map <String,String> paramMap, int curNum, int pageNum);

	int queryTopbpmProcessByAppidCount(Map <String,String> paramMap);

	void updateAppointErrorProcessStatus(Map<String, String> paramMap);

	int queryActivityInfoCount(Map<String, String> paramMap);

	List<WorkflowCallback> queryActivityInfo(Map<String, String> paramMap, int curNum, int pageNum);

	int queryProcessvariableInfoCount(Map<String, String> paramMap);

	List<WorkflowCallback> queryProcessvariableInfo(Map<String, String> paramMap, int curNum, int pageNum);

	int queryProcessvariable_tInfoCount(Map<String, String> paramMap);

	List<WorkflowCallback> queryProcessvariable_tInfo(Map<String, String> paramMap, int curNum, int pageNum);

	int queryProcess_tDetailCount(Map<String, String> paramMap);

	List<WorkflowCallback> queryProcess_tDetail(Map<String, String> paramMap, int curNum, int pageNum);

	void updateProcessvariableInfo(WorkflowCallback workflowCallback);

	void updateProcessvariable_tInfo(WorkflowCallback workflowCallback);

	void updateProcess_tInfo(WorkflowCallback workflowCallback);

	void updateTierAppidStatus(Map<String,String> paramMap);

	void updateWeiguidangAppidStatus(Map<String, String> paramMap);

}
