package com.huaxia.opas.service.report.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.report.ApplyStatisticsDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.report.ApplyStatisticsService;

public class ApplyStatisticsServiceImpl extends AbstractService implements ApplyStatisticsService, Serializable {
	private static final long serialVersionUID = -2094526262335574884L;
	@Resource(name = "applyStatisticsDao")
	private ApplyStatisticsDao applyStatisticsDao;
	
	public ApplyStatisticsDao getApplyStatisticsDao() {
		return applyStatisticsDao;
	}

	public void setApplyStatisticsDao(ApplyStatisticsDao applyStatisticsDao) {
		this.applyStatisticsDao = applyStatisticsDao;
	}

	@Override
	public List<Map<String, Object>> queryApplyStatistics(Map<String, Object> paraMap, int curNum, int pageNum) {
		List<Map<String, Object>> resultList = applyStatisticsDao.queryApplyStatistics(paraMap,curNum,pageNum);
		return resultList;
	}
	@Override
	public int queryApplyStatisticsCount(Map<String, Object> paraMap) {
		return applyStatisticsDao.queryApplyStatisticsCount(paraMap);
	}
	

}
