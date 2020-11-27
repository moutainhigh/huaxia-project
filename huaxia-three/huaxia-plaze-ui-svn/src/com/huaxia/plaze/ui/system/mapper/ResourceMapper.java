package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huaxia.plaze.ui.system.domain.Dictionary;

public interface ResourceMapper<T> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	T selectObjectById(@Param("id") String id);
	
	int deleteObjectById(String id);
	
	int updateObjectStatusByArguments(@Param("id") String id, @Param("status") String status);
	
	int selectListCountByParentId(String pid);
	
	int selectListCountByPath(String path);
	
	List<T> selectList();
	
	List<Dictionary> selectDictionaryList();
	
	int selectChooseListCountByPage(Map<String, Object> args);

	List<T> selectChooseListByPage(Map<String, Object> args);
	
	T selectNextObjectByKey(String id);
	
	int insertObject(T t);
	
	List<T> selectTreeList();
	
	int updateObject(T t);
	
}
