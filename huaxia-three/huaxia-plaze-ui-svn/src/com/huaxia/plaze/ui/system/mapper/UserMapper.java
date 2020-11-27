package com.huaxia.plaze.ui.system.mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper<T> {
	
	int selectListCountByPage(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	T selectByUserAcct(String account);	
	
	T selectById(String id);
	
	int insert(T t);
	
	int updateByUser(T t);

	int updateWithParamsByAcct(Map<String, Object> args);
	
	int updateWithParamsById(Map<String, Object> args);
	int updateForResetById(Map<String, Object> args);

	int selectCountExistByKeyParam(Map<String, Object> args);
	
	int selectCountSameByKeyParam(Map<String, Object> args);
	
}
