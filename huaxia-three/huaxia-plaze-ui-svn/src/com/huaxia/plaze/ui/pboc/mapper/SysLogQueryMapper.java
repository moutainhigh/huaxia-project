package com.huaxia.plaze.ui.pboc.mapper;

import java.util.List;
import java.util.Map;

public interface SysLogQueryMapper<T> {

	int insertObject(T t);
	
	int selectListCountByPage(Map<String, Object> args);
	
	int selectListCountByPageEx(Map<String, Object> args);

	List<T> selectListByPage(Map<String, Object> args);
	
	List<T> selectListByPageEx(Map<String, Object> args);

	List<T> selectListBy(Map<String, Object> args);

	int selectCountByTime(Map<String, Object> map);

	int selectMaxCountByMonth(Map<String, Object> map);

	int selectCountByDay(Map<String, Object> map);

	int selectCountByMonth(Map<String, Object> map);

	int selectCountByDayAndCert(Map<String, Object> map);
	
	List<Map<String, Object>> selectListByArguments(Map<String, Object> args);
	
}
