package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotQueue;

public interface AllotQueueService {
	 int saveAllotQueue(AllotQueue allotQueue) throws CoreException;

	 int updateAllotQueue(AllotQueue allotQueue) throws CoreException;

	int removeAllotQueueById(String queueId) throws CoreException;

	// 分页查询
	List<AllotQueue> queryAllAllotQueue(AllotQueue allotQueue, int page, int rows) throws CoreException;

	int countAllotQueue(AllotQueue allotQueue) throws CoreException;
	
	List<AllotQueue> queryAllotQueue(Map<String,Object> map) throws CoreException;

	// 查询全部队列
	int queryCountAllotQueue(AllotQueue allotQueue) throws CoreException;

	AllotQueue queryAllotQueueByCode(String queueCode) throws CoreException;

	int queryQueueLevel(String queueCode) throws CoreException;
	
	List<AllotQueue> queryAllotQueue2(Map<String,Object> map) throws CoreException;
	
	
}
