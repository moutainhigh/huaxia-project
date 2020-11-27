package com.huaxia.plaze.ui.setting.service;

import java.util.List;

import com.huaxia.plaze.common.PageProperty;
import com.huaxia.plaze.ui.setting.domain.QueryExclude;

public interface QueryExcludeService {

	/** 分页总数量 */
	int queryListCountByPage(PageProperty property);

	/** 分页总记录 */
	List<QueryExclude> queryListByPage(PageProperty property);
	
	/** 保存查询例外记录 */
	int save(QueryExclude queryExclude);
	
	/** 删除指定编号的查询例外记录 */
	int removeById(String id);
	
}
