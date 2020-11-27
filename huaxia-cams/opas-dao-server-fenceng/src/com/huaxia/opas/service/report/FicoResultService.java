package com.huaxia.opas.service.report;

import java.util.Map;

public interface FicoResultService {

	//查询FICO大数据汇总报表
	public Map<String, Object> queryFicoSummaryDataByDate(Map<String, Object> paraMap);

	//FICO明细报表
	public Map<String, Object> queryFicoDetailDataByDate(Map<String, Object> paraMap, int pageNum,
			int pageRows);

}
