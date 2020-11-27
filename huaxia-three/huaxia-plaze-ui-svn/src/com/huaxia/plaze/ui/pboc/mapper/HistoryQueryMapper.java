package com.huaxia.plaze.ui.pboc.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.pboc.domain.HistoryQuery;

public interface HistoryQueryMapper<T> {

	int selectListCountByPage(Map<String, Object> args);

	List<HistoryQuery> selectListByPage(Map<String, Object> args);

}
