package com.huaxia.plaze.ui.numread.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.numread.domain.NumReadLog;
import com.huaxia.plaze.ui.unicom.domain.PhoneQueryLog;

public interface LogService {

	int queryLogListCountByPage(PageProperty page);

	List<NumReadLog> queryLogListByPage(PageProperty page);

}
