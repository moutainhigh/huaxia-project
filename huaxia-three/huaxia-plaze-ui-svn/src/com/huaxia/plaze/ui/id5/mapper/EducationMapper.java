package com.huaxia.plaze.ui.id5.mapper;

import java.util.List;
import java.util.Map;

import com.huaxia.plaze.ui.id5.domain.Education;

public interface EducationMapper {

	int selectHistoryListCountByPage(Map<String, Object> args);

	List<Education> selectHistoryListByPage(Map<String, Object> args);

	Education selectResultByTrnId(String trnId);
	
	int updateStatusById(Map<String, Object> args);
	
	void selectQueryResultBySource(Map<String, Object> args);
}
