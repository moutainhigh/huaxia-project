package com.huaxia.plaze.ui.pboc.service;

import com.huaxia.plaze.ui.pboc.domain.SingleQueryReview;
import com.huaxia.plaze.ui.system.domain.User;

// 人行查询异常行为监控
public interface QueryMonitorService {

	// 单条实时查询（提交复核）阻断设置
	String checkLimit(SingleQueryReview queryView, User loginUser, String queryExclude) throws Exception;

	// 单条实时查找（历史数据查询）阻断设置
	String checkLimitSearch(String certNo, User loginUser, String queryExclude) throws Exception;
	
	public String  queryInterfaceSetting(String queryFrom);

}
