package com.huaxia.opas.dao.workflow.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.workflow.WorkflowDao;
import com.huaxia.opas.domain.workflow.Workflow;
import com.huaxia.opas.domain.workflow.WorkflowCallback;

public class WorkflowDaoImpl extends AbstractDAO implements WorkflowDao {// jpc0522										
	
	private static final long serialVersionUID = -9039503076676542644L;
	private static final String NAMESPACES = "Workflow.";

	@Override
	public List<Workflow> selectWorkflow(Workflow workflow) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "selectWorkflow", workflow, workflow.getCurNum(),
				workflow.getPageNum());
	}

	@Override
	public int selectCount(Workflow workflow) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "selectCount", workflow);
	}

//	@Override
//	public int queryTopbpmExceSolveAppidCount(WorkflowCallback workflowCallback) {
//		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryTopbpmExceSolveAppidCount", workflowCallback);
//	}
//
//	@Override
//	public List<WorkflowCallback> queryTopbpmExceSolveAppid(WorkflowCallback workflowCallback) {
//		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryTopbpmExceSolveAppid", workflowCallback, workflowCallback.getCurNum(),
//				workflowCallback.getPageNum());
//	}
	@Override
	public int queryTopbpmExceSolveAppidCount(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTopbpmExceSolveAppidCount",paramMap);
	}
	
	@Override
	public List<WorkflowCallback> queryTopbpmExceSolveAppid(Map<String, String> paramMap,int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryTopbpmExceSolveAppid",paramMap,curNum,pageNum);
	}

	@Override
	public int insertTopbpmTmpExecution(String appId) {
		return getSqlMapWorkflow().insert(NAMESPACES +"insertTopbpmTmpExecution",appId);
	}

	@Override
	public int updateTopbpmExceSolve(String appId) {
		return getSqlMap().update(NAMESPACES +"updateTopbpmExceSolve",appId);
	}

	@Override
	public int queryWeiguidangAppidCount(Map<String, String> paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWeiguidangAppidCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryWeiguidangAppid(Map<String, String> paramMap,int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryWeiguidangAppid",paramMap,curNum,pageNum);
	}

//	@Override
//	public int queryTopbpmExceSolveArtiAppidCount(Map<String, String> paramMap) {
//		return getSqlMap().queryForObject(NAMESPACES + "queryTopbpmExceSolveArtiAppidCount",paramMap);
//	}
//
//	@Override
//	public List<WorkflowCallback> queryTopbpmExceSolveArtiAppid(Map<String, String> paramMap, int curNum, int pageNum) {
//		return getSqlMap().queryForList(NAMESPACES + "queryTopbpmExceSolveArtiAppid",paramMap,curNum,pageNum);
//	}

	@Override
	public int insertExceptionTopbpmTmpExecution(String appId) {
		return getSqlMapWorkflow().insert(NAMESPACES +"insertExceptionTopbpmTmpExecution",appId);
		
	}

	@Override
	public int updateTopbpmException(String appId) {
		return getSqlMapWorkflow().update(NAMESPACES +"updateTopbpmException",appId);
		
	}

	@Override
	public int queryTopbpmExceSolveAutoAppidCount(Map<String, String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryTopbpmExceSolveAutoAppidCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryTopbpmExceSolveAutoAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryTopbpmExceSolveAutoAppid",paramMap,curNum,pageNum);
	}

	@Override
	public int updateErrorExceptionStatus() {
		return getSqlMapWorkflow().update(NAMESPACES + "updateErrorExceptionStatus");
	}

	@Override
	public List<WorkflowCallback> queryTopbpmTmpExecutionAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryTopbpmTmpExecutionAppid",paramMap,curNum,pageNum);
	}

	@Override
	public int queryTopbpmTmpExecutionAppidCount(Map<String, String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryTopbpmTmpExecutionAppidCount",paramMap);
	}

	@Override
	public Map<String, Object> queryTopbpmTmpExecutionAppidDetail(Map<String, String> paramMap) {
		return  getSqlMapWorkflow().queryForObject(NAMESPACES + "queryTopbpmTmpExecutionAppidDetail",paramMap);
	}

	@Override
	public void updateTopbpmTmpExecutionDetail(Map<String, String> paramMap) {
		getSqlMapWorkflow().update(NAMESPACES + "updateTopbpmTmpExecutionDetail", paramMap);
		
	}

	@Override
	public void deleteTopbpmTmpExecutionDetail(Map<String, String> paramMap) {
		getSqlMapWorkflow().delete(NAMESPACES + "deleteTopbpmTmpExecutionDetail", paramMap);
		
	}

	@Override
	public void updateAppointErrorExceptionStatus(Map<String,String> paramMap) {
		getSqlMapWorkflow().update(NAMESPACES + "updateAppointErrorExceptionStatus", paramMap);
		
	}

	@Override
	public Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap) {
		return  getSqlMapWorkflow().queryForObject(NAMESPACES + "queryTopbpmExpMessageDetail",paramMap);
	}

	@Override
	public void insertTmpSingle(Map<String, String> paramMap) {
		getSqlMapWorkflow().insert(NAMESPACES +"insertExceptionTopbpmTmpExecutionSpecial",paramMap);
		
	}

	@Override
	public int queryTopbpmProcessByAppidCount(Map <String,String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryTopbpmProcessByAppidCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryTopbpmProcessByAppid(Map <String,String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryTopbpmProcessByAppid",paramMap,curNum,pageNum);
	}

	@Override
	public void updateAppointErrorProcessStatus(Map<String, String> paramMap) {
		getSqlMapWorkflow().update(NAMESPACES + "updateAppointErrorProcessStatus", paramMap);
		
	}

	@Override
	public int queryActivityInfoCount(Map<String, String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryActivityInfoCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryActivityInfo(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryActivityInfo",paramMap,curNum,pageNum);
	}

	@Override
	public int queryProcessvariableInfoCount(Map<String, String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryProcessvariableInfoCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryProcessvariableInfo(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryProcessvariableInfo",paramMap,curNum,pageNum);
	}

	@Override
	public int queryProcessvariable_tInfoCount(Map<String, String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryProcessvariable_tInfoCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryProcessvariable_tInfo(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryProcessvariable_tInfo",paramMap,curNum,pageNum);
	}

	@Override
	public int queryProcess_tDetailCount(Map<String, String> paramMap) {
		return getSqlMapWorkflow().queryForObject(NAMESPACES + "queryProcess_tDetailCount",paramMap);
	}

	@Override
	public List<WorkflowCallback> queryProcess_tDetail(Map<String, String> paramMap, int curNum, int pageNum) {
		return getSqlMapWorkflow().queryForList(NAMESPACES + "queryProcess_tDetail",paramMap,curNum,pageNum);
	}

	@Override
	public void updateProcessvariableInfo(WorkflowCallback workflowCallback) {
		getSqlMapWorkflow().update(NAMESPACES + "updateProcessvariableInfo", workflowCallback);
	}

	@Override
	public void updateProcessvariable_tInfo(WorkflowCallback workflowCallback) {
		getSqlMapWorkflow().update(NAMESPACES + "updateProcessvariable_tInfo", workflowCallback);
	}

	@Override
	public void updateProcess_tInfo(WorkflowCallback workflowCallback) {
		getSqlMapWorkflow().update(NAMESPACES + "updateProcess_tInfo", workflowCallback);
	}

	@Override
	public void updateTierAppidStatus(Map<String,String> paramMap) {
		getSqlMap().update(NAMESPACES + "updateTierAppidStatus", paramMap);
	}

	@Override
	public void updateWeiguidangAppidStatus(Map<String, String> paramMap) {
		getSqlMap().update(NAMESPACES + "updateWeiguidangAppidStatus", paramMap);
	}

}
