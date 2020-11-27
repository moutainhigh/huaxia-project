package com.huaxia.opas.service.workflow.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.huaxia.opas.dao.workflow.WorkflowDao;
import com.huaxia.opas.domain.workflow.Workflow;
import com.huaxia.opas.domain.workflow.WorkflowCallback;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.workflow.WorkflowQuService;

public class WorkflowQuServiceImpl extends AbstractService implements WorkflowQuService,Serializable {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(WorkflowQuServiceImpl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1558350836448238912L;
	@Resource(name = "workflowDao")
	private WorkflowDao workflowDao;

	@Override
	public Map<String, Object> selectWorkflow(Workflow workflow) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Workflow> rows = new ArrayList<>();
		int total = workflowDao.selectCount(workflow);
		if (total > 0) {
			rows = workflowDao.selectWorkflow(workflow);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
//	public Map<String, Object> queryTopbpmExceSolveAppid(WorkflowCallback workflowCallback) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<WorkflowCallback> rows = new ArrayList<>();
//		int total = workflowDao.queryTopbpmExceSolveAppidCount(workflowCallback);
//		if (total > 0) {
//			rows = workflowDao.queryTopbpmExceSolveAppid(workflowCallback);
//		}
//		map.put("total", total);
//		map.put("rows", rows);
//		return map;
//	}
	public Map<String, Object> queryTopbpmExceSolveAppid(Map<String, String> paramMap,int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryTopbpmExceSolveAppidCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryTopbpmExceSolveAppid(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

//	@Override
//	public Map<String, Object> queryTopbpmExceSolveArtiAppid(Map<String, String> paramMap, int curNum, int pageNum) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
//		int total = workflowDao.queryTopbpmExceSolveArtiAppidCount(paramMap);
//		if (total > 0) {
//			rows = workflowDao.queryTopbpmExceSolveArtiAppid(paramMap,curNum,pageNum);
//		}
//		map.put("total", total);
//		map.put("rows", rows);
//		return map;
//	}

	@Override
	public void insertTopbpmTmpExecutionAndUpdateStatus(String appId) {
		workflowDao.insertTopbpmTmpExecution(appId);
		workflowDao.updateTopbpmExceSolve(appId);
	}

	@Override
	public Map<String, Object> queryWeiguidangAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryWeiguidangAppidCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryWeiguidangAppid(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public void solveExceptionSingleOrBatch(String appId) {
		workflowDao.insertExceptionTopbpmTmpExecution(appId);
//		workflowDao.updateTopbpmException(appId);
		
	}

	@Override
	public Map<String, Object> queryTopbpmExceSolveAutoAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryTopbpmExceSolveAutoAppidCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryTopbpmExceSolveAutoAppid(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public int updateErrorExceptionStatus() {
		return workflowDao.updateErrorExceptionStatus();
	}

	@Override
	public Map<String, Object> queryTopbpmTmpExecutionAppid(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryTopbpmTmpExecutionAppidCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryTopbpmTmpExecutionAppid(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public Map<String, Object> queryTopbpmTmpExecutionAppidDetail(Map<String, String> paramMap) {
		return workflowDao.queryTopbpmTmpExecutionAppidDetail(paramMap);
	}

	@Override
	public void updateTopbpmTmpExecutionDetail(Map<String, String> paramMap) {
		workflowDao.updateTopbpmTmpExecutionDetail(paramMap);
		
	}

	@Override
	public void deleteTopbpmTmpExecutionDetail(Map<String, String> paramMap) {
		workflowDao.deleteTopbpmTmpExecutionDetail(paramMap);
		
	}

	@Override
	public void updateAppointErrorExceptionStatus(Map<String,String> paramMap) {
		workflowDao.updateAppointErrorExceptionStatus(paramMap);
		
	}

	@Override
	public Map<String, Object> queryTopbpmExpMessageDetail(Map<String, String> paramMap) {
		return workflowDao.queryTopbpmExpMessageDetail(paramMap);
	}

	@Override
	public void insertTmpSingle(Map<String, String> paramMap) {
		workflowDao.insertTmpSingle(paramMap);
		
	}

	@Override
	public Map<String, Object> queryTopbpmProcessByAppid(Map <String,String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryTopbpmProcessByAppidCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryTopbpmProcessByAppid(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public void updateAppointErrorProcessStatus(Map<String, String> paramMap) {
		workflowDao.updateAppointErrorProcessStatus(paramMap);
		
	}

	@Override
	public Map<String, Object> queryActivityInfo(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryActivityInfoCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryActivityInfo(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public Map<String, Object> queryProcessvariableInfo(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryProcessvariableInfoCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryProcessvariableInfo(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public Map<String, Object> queryProcessvariable_tInfo(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryProcessvariable_tInfoCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryProcessvariable_tInfo(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public Map<String, Object> queryProcess_tDetail(Map<String, String> paramMap, int curNum, int pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WorkflowCallback> rows = new ArrayList<WorkflowCallback>();
		int total = workflowDao.queryProcess_tDetailCount(paramMap);
		if (total > 0) {
			rows = workflowDao.queryProcess_tDetail(paramMap,curNum,pageNum);
		}
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}

	@Override
	public void updateProcessvariableInfo(WorkflowCallback workflowCallback) {
		workflowDao.updateProcessvariableInfo(workflowCallback);
	}

	@Override
	public void updateProcessvariable_tInfo(WorkflowCallback workflowCallback) {
		workflowDao.updateProcessvariable_tInfo(workflowCallback);
	}

	@Override
	public void updateProcess_tInfo(WorkflowCallback workflowCallback) {
		workflowDao.updateProcess_tInfo(workflowCallback);
	}

	@Override
	public void updateTierAppidStatus(Map<String,String> paramMap) {
		workflowDao.updateTierAppidStatus(paramMap);
	}

	@Override
	public void updateWeiguidangAppidStatus(Map<String, String> paramMap) {
		workflowDao.updateWeiguidangAppidStatus(paramMap);
	}


}
