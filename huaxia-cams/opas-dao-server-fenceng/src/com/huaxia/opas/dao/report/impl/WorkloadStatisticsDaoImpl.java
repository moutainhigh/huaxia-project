package com.huaxia.opas.dao.report.impl;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.report.WorkloadStatisticsDao;

public class WorkloadStatisticsDaoImpl extends AbstractDAO implements WorkloadStatisticsDao{
	private static final long serialVersionUID = -4790813122653113071L;
	private static final String NAMESPACES = "WorkloadStatistics.";
	@Override
	public int queryWorkloadStatisticsCount(Map<String, Object> paramsMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryWorkloadStatisticsCount", paramsMap);
	}
	
	@Override
	public List<Map<String, Object>> queryWorkloadStatistics(Map<String, Object> paramsMap, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryWorkloadStatistics", paramsMap, curNum, pageNum);
	}


}
