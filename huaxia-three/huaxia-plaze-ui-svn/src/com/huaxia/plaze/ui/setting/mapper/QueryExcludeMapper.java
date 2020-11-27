package com.huaxia.plaze.ui.setting.mapper;

import java.util.List;
import java.util.Map;

public interface QueryExcludeMapper<T> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	int insert(T t);
	
	int deleteById(String id);
	
	String selectExclude(Map<String, Object> args);
	
}
