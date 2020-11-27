package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.TaskRequestInfoDao;
import com.huaxia.opas.domain.TaskRequestInfo;
import com.huaxia.opas.mapper.TaskRequestInfoMapper;

@Repository
public class TaskRequestInfoDaoImpl implements TaskRequestInfoDao {
	
	@Resource
	private TaskRequestInfoMapper taskRequestInfoMapper;

	public TaskRequestInfoMapper getTaskRequestInfoMapper() {
		return taskRequestInfoMapper;
	}

	public void setTaskRequestInfoMapper(TaskRequestInfoMapper taskRequestInfoMapper) {
		this.taskRequestInfoMapper = taskRequestInfoMapper;
	}

	@Override
	public int insert(TaskRequestInfo taskRequestInfo) {
		return getTaskRequestInfoMapper().insert(taskRequestInfo);
	}

	@Override
	public int update(TaskRequestInfo taskRequestInfo) {
		return getTaskRequestInfoMapper().update(taskRequestInfo);
	}

}
