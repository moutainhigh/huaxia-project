package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;
public interface WorkloadStatisticsService {
	public List<Map<String, Object>> queryWorkloadStatistics(Map<String, Object> paraMap, int curNum, int pageNum);

	public int queryWorkloadStatisticsCount(Map<String, Object> paraMap);
}
