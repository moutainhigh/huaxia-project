package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

public interface TeamMapper<T> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	int selectListCountByMapping(String id);
	
	T selectById(String id);
	
	List<T> selectAllList();
	
	int insert(T t);
	
	int updateStatusById(String id);
	
	int update(T t);
	
	int updateByParams(Map<String, Object> args);
	
}
