package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.TaskRequestInfoDao;
import com.huaxia.opas.domain.TaskRequestInfo;
import com.huaxia.opas.service.TaskRequestInfoService;

@Service("taskRequestInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskRequestInfoServiceImpl implements TaskRequestInfoService {

	@Autowired
	private TaskRequestInfoDao taskRequestInfoDao;

	public TaskRequestInfoDao getTaskRequestInfoDao() {
		return taskRequestInfoDao;
	}

	public void setTaskRequestInfoDao(TaskRequestInfoDao taskRequestInfoDao) {
		this.taskRequestInfoDao = taskRequestInfoDao;
	}

	@Override
	public int save(TaskRequestInfo taskRequestInfo) {
		return getTaskRequestInfoDao().insert(taskRequestInfo);
	}

	@Override
	public int update(TaskRequestInfo taskRequestInfo) {
		return getTaskRequestInfoDao().update(taskRequestInfo);
	}

}
