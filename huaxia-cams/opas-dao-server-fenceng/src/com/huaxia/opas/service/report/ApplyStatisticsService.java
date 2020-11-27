package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;

public interface ApplyStatisticsService {
	public List<Map<String, Object>> queryApplyStatistics(Map<String, Object> paraMap, int curNum, int pageNum);

	public int queryApplyStatisticsCount(Map<String, Object> paraMap);
}
