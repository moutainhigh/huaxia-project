package com.huaxia.opas.dao.tripartite.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.tripartite.TaskCallInfoDao;
import com.huaxia.opas.domain.tripartite.TaskCallInfo;
import com.huaxia.opas.mapper.tripartite.TaskCallInfoMapper;

@Repository
public class TaskCallInfoDaoImpl implements TaskCallInfoDao {

	@Resource
	private TaskCallInfoMapper taskCallInfoMapper;

	public TaskCallInfoMapper getTaskCallInfoMapper() {
		return taskCallInfoMapper;
	}

	public void setTaskCallInfoMapper(TaskCallInfoMapper taskCallInfoMapper) {
		this.taskCallInfoMapper = taskCallInfoMapper;
	}

	@Override
	public List<Map<String, String>> selectAllTaskForWorkflow(Map<String, Object> param) {
		return taskCallInfoMapper.selectAllTaskForWorkflow(param);
	}

	@Override
	public int insertSuccessInfoByAppId(String appId) {
		return taskCallInfoMapper.insertSuccessInfoByAppId(appId);
	}

	@Override
	public int deleteTaskCallInfo(Map<String, Object> param) {
		return taskCallInfoMapper.deleteTaskCallInfo(param);
	}

	@Override
	public void updateTaskInfoStatus(Map<String, Object> param) {
		taskCallInfoMapper.updateTaskInfoStatus(param);
	}

	@Override
	public List<Map<String, String>> selectFormularyNumTasksByType(Map<String, Object> params) {
		return taskCallInfoMapper.selectFormularyNumTasksByType(params);
	}

	@Override
	public void updateBatchStatus(Map<String, Object>params) {
		taskCallInfoMapper.updateBatchStatus(params);
	}

	@Override
	public void updateSingleTaskStatus(Map<String, Object> params) {
		taskCallInfoMapper.updateSingleTaskStatus(params);
	}

	@Override
	public void insertBatch(Map<String,Object> params) {
		taskCallInfoMapper.insertBatch(params);
	}

	@Override
	public int updateSingleTask(String appId, String status, String taskType, String errorCode, String lstOptUser,
			String requestAddNum, String failureAddNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("status", status);
		params.put("taskType", taskType);
		params.put("errorCode", errorCode);
		params.put("lstOptUser", lstOptUser);
		params.put("requestAddNum", requestAddNum);
		params.put("failureAddNum", failureAddNum);
		return taskCallInfoMapper.updateSingleTask(params);
	}

	@Override
	public void insertTaskCallInfoHis(String appId, String taskType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appId", appId);
		params.put("taskType", taskType);
		taskCallInfoMapper.insertTaskCallInfoHis(params);
	}
	@Override
	public void insertTaskCallInfoHisBatch(Map<String, Object> params) {
		taskCallInfoMapper.insertTaskCallInfoHisBatch(params);
	}

	@Override
	public void updateTaskCallInfoBatch(Map<String, Object> params) {
		taskCallInfoMapper.updateTaskCallInfoBatch(params);
	}

	@Override
	public int updateTaskCallInfoInteract(String sourceId, String status, String taskType, String errorCode,
			String lstOptUser, String requestAddNum, String failureAddNum) {
		return taskCallInfoMapper.updateTaskCallInfoInteract(sourceId, status, taskType, errorCode,
				lstOptUser, requestAddNum, failureAddNum);
	}

	@Override
	public int insertTaskCallInfoInteractHis(String sourceId, String taskType) {
		return taskCallInfoMapper.insertTaskCallInfoInteractHis(sourceId, taskType);
	}

	@Override
	public String selectAppIdTaskInfoInteract(String sourceId, String taskType, String taskStatus) {
		return taskCallInfoMapper.selectAppIdTaskInfoInteract(sourceId, taskType, taskStatus);
	}

	@Override
	public int updateTaskStatusNotTypeHisBatch(String taskStatus, Integer requestAddNum, Integer hourNum,
			Integer requestNum, String updateStatus, String lstOptUser, Map<String, String> taskTypeMap) {
		return taskCallInfoMapper.updateTaskStatusNotTypeHisBatch(taskStatus, requestAddNum, hourNum,
				requestNum, updateStatus, lstOptUser, taskTypeMap);
	}
}