package com.huaxia.plaze.ui.system.service;

import java.util.List;
import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.system.domain.OperateLog;

public interface OperateLogService {
	
	int saveOperateLog(OperateLog log);
	
	int queryOperateListCountByPage(PageProperty property);
	List<OperateLog> queryOperateListByPage(PageProperty property);
}
