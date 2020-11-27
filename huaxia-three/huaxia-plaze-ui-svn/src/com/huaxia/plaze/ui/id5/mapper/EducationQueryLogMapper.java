package com.huaxia.plaze.ui.id5.mapper;

import java.util.List;
import java.util.Map;
import com.huaxia.plaze.ui.id5.domain.EducationQueryLog;
public interface EducationQueryLogMapper {


	int insertObject(EducationQueryLog log);
	int selectLogListCountByPage(Map<String, Object> args);

	List<EducationQueryLog> selectLogListByPage(Map<String, Object> args);
	int updateQueryResult(Map<String, Object> args);


	
}
