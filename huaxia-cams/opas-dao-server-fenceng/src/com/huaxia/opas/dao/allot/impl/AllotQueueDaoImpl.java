package com.huaxia.opas.dao.allot.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotQueueDao;
import com.huaxia.opas.domain.allot.AllotQueue;

public class AllotQueueDaoImpl extends AbstractDAO implements AllotQueueDao {

	private static final long serialVersionUID = -2334346946973306541L;
	private static final String NAMESPACES = "AllotQueue.";
	//增加队列
	@Override
	public int insertAllotQueue(AllotQueue allotQueue) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAllotQueue", allotQueue);
	}
	//更改队列信息
	@Override
	public int updateAllotQueue(AllotQueue allotQueue) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAllotQueue", allotQueue);
	}
	//删除队列
	@Override
	public int deleteAllotQueueById(String queueId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllotQueueById", queueId);
	}
	//根据队列id  查询队列
	@Override
	public AllotQueue selectAllotQueueByCode(String queueCode) throws CoreException {
		return (AllotQueue)(getSqlMap().queryForObject(NAMESPACES + "selectAllotQueueByCode", queueCode));
	}
	
	@Override
	public int selectCountAllotQueue(AllotQueue allotQueue) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES + "selectCountAllotQueue",allotQueue);
	} 
	
	//分页查询队列信息
	@Override
	public List<AllotQueue> selectAllAllotQueue(AllotQueue allotQueue, int currentPage, int pageSize) throws CoreException {
		List<AllotQueue> list = new ArrayList<AllotQueue>();
		list=getSqlMap().queryForList(NAMESPACES + "selectAllAllotQueue",allotQueue,(currentPage -1) * pageSize, pageSize);
		return list;
	}
	@Override
	public int countAllotQueue(AllotQueue allotQueue) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES + "countAllotQueue",allotQueue);
	}
	//查询全部队列
	@Override
	public List<AllotQueue> selectAllAllotQueue() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectAllAllotQueue");
	}
	//查询队列等级
	@Override
	public int queryQueueLevel(String queueCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryQueueLevel", queueCode);
	}
	
	@Override
	public List<AllotQueue> selectCreditDict() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectCreditDict");
	}
	
}
