package com.huaxia.plaze.ui.pboc.service;



import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.pboc.domain.HistoryQuery;
import com.huaxia.plaze.ui.pboc.domain.SysLogQuery;

// 查询历史数据查询业务处理层
public interface HistoryQueryService {

	int queryListCountByPage(PageProperty page);

	List<HistoryQuery> queryListByPage(PageProperty page);

	

	


}
