package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.ApplyStatisticsDao;


public class ApplyStatisticsDaoImpl extends AbstractDAO implements ApplyStatisticsDao{
	private static final long serialVersionUID = -8058677710268061879L;
	private static final String NAMESPACES = "ApplyStatistics.";
	@Override
	public int queryApplyStatisticsCount(Map<String, Object> paramsMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryApplyStatisticsCount", paramsMap);
	}
	
	@Override
	public List<Map<String, Object>> queryApplyStatistics(Map<String, Object> paramsMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryApplyStatistics", paramsMap, curNum, pageNum);
	}


}
