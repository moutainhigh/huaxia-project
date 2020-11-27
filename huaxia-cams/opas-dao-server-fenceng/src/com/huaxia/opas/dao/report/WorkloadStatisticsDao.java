package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

public interface WorkloadStatisticsDao {
	public int queryWorkloadStatisticsCount(Map<String, Object> paramsMap);
	
	public List<Map<String, Object>> queryWorkloadStatistics(Map<String, Object> paramsMap, int curNum, int pageNum);

}