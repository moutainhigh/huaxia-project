package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

public interface FicoResultDao {

	//Fico报表汇总
	public List<Map<String, Object>> selectFicoSummaryResultDataByDate(Map<String, Object> paramsMap);
	//Fico报表明细
	public List<Map<String, Object>> selectFicoDetailDataByDate(Map<String, Object> paramsMap, int pageNum, int pageRows);
	public String getCountFicoDetailDataByDate(Map<String, Object> paramsMap);

}
