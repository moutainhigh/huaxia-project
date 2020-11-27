package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.report.WorkloadStatisticsDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.report.WorkloadStatisticsService;

public class WorkloadStatisticsServiceImpl extends AbstractService implements WorkloadStatisticsService, Serializable{
	private static final long serialVersionUID = -6874721721177180235L;
	@Resource(name = "workloadStatisticsDao")
	private WorkloadStatisticsDao workloadStatisticsDao;
	
	public WorkloadStatisticsDao getWorkloadStatisticsDao() {
		return workloadStatisticsDao;
	}
	public void setWorkloadStatisticsDao(WorkloadStatisticsDao workloadStatisticsDao) {
		this.workloadStatisticsDao = workloadStatisticsDao;
	}
	@Override
	public List<Map<String, Object>> queryWorkloadStatistics(Map<String, Object> paraMap, int curNum, int pageNum) {
		List<Map<String, Object>> resultList = workloadStatisticsDao.queryWorkloadStatistics(paraMap,curNum,pageNum);
		return resultList;
	}
	@Override
	public int queryWorkloadStatisticsCount(Map<String, Object> paraMap) {
		return workloadStatisticsDao.queryWorkloadStatisticsCount(paraMap);
	}
	
}
