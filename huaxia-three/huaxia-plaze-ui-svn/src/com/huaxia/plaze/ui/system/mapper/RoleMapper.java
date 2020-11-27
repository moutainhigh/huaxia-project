package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper<T> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	int selectListCountByMapping(String id);
	
	int updateStatusById(String id);
	
	List<T> selectAllList();
	
	int insert(T t);
	
	int update(T t);
	
	T selectById(String id);
	
}
