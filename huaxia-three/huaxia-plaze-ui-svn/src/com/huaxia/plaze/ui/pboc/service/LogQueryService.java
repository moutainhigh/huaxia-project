package com.huaxia.plaze.ui.pboc.service;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.SysLogQuery;

// 查询日志监控业务处理层
public interface LogQueryService {

	int saveObject(SysLogQuery query);

	int queryListCountByPage(PageProperty page);

	int queryListCountByPageEx(PageProperty page);

	List<SysLogQuery> queryListByPage(PageProperty page);

	List<SysLogQuery> queryListByPageEx(PageProperty page);

	List<SysLogQuery> queryList(PageProperty page);

	/**
	 * 查询日志报表
	 * 
	 * @param args
	 *            查询参数
	 * @return 指定查询参数的结果集
	 * @since 2019-01-11
	 * @author zhiguo.li
	 * @version 1.0
	 */
	List<Map<String, Object>> queryListByArguments(Map<String, Object> args);

}
