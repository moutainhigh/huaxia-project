package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.system.domain.Log;

public interface LogMapper<T extends Log> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	int insert(T t);
	
}
