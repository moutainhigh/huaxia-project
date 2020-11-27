package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

public interface ApplyStatisticsDao {
	public int queryApplyStatisticsCount(Map<String, Object> paramsMap);
	
	public List<Map<String, Object>> queryApplyStatistics(Map<String, Object> paramsMap, int curNum, int pageNum);

}