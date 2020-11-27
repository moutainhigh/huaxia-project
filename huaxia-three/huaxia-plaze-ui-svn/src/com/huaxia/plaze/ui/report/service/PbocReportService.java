package com.huaxia.plaze.ui.report.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.PageProperty;

/**
 * 人行报表统计服务接口
 * @author zhiguo.li
 * @since 2019-01-14
 * @version 1.0
 *
 */
public interface PbocReportService {
	
	int generateCountReport();
	
	int queryListCountReportByPage(PageProperty property);
	
	List<Map<String, Object>> queryListReportByPage(PageProperty property);

}
