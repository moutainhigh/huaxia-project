package com.huaxia.opas.dao.allot;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotQueue;

public interface AllotQueueDao {
	 int insertAllotQueue(AllotQueue allotQueue) throws CoreException;

	 int updateAllotQueue(AllotQueue allotQueue) throws CoreException;

	 int deleteAllotQueueById(String queueId) throws CoreException;

	// 分页查询
	List<AllotQueue> selectAllAllotQueue(AllotQueue allotQueue, int page, int rows) throws CoreException;

	int countAllotQueue(AllotQueue allotQueue) throws CoreException;
	
	 List<AllotQueue> selectAllAllotQueue() throws CoreException;

	// 查询全部队列

	AllotQueue selectAllotQueueByCode(String queueCode) throws CoreException;
	
	int selectCountAllotQueue(AllotQueue allotQueue) throws CoreException;

	int queryQueueLevel(String queueCode) throws CoreException;
	
	//业务字典查询
	 List<AllotQueue> selectCreditDict() throws CoreException;
}
