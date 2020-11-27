package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

public interface StaffMapper<T> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	T selectObjectByUserAcct(String account);
	
	int insertObject(T t);

	int updateObjectByWhere(Map<String, Object> args);
	
	int deleteObjectMappingByKey(String id);
	
	T selectObjectById(String id);
	
}
