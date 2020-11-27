package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

public interface RoleResourceMapper<T> {
	
	List<T> selectListByAccount(String account);
	
	List<Map<String, String>> selectListMapping();
	
	int deleteListMappingByKey(String id);
	
	int insertListMapping(Map<String, Object> args);
	
	List<Map<String, String>> selectTreeListMappingByKey(String id);
	
}
