package com.huaxia.plaze.ui.system.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;

public interface LogService<T> {
	
	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<T> queryListByPage(PageProperty property);

}
