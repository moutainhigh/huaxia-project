package com.huaxia.plaze.ui.id5.mapper;


import java.util.Map;

import com.huaxia.plaze.ui.id5.domain.Education;
import com.huaxia.plaze.ui.id5.domain.EducationSingle;

public interface EducationSingleMapper {

	int insert(EducationSingle educationSingle);

	int updateStatusById(Map<String, Object> args);
	
	int query24HoursCountByParams(Map<String, Object> args);
	
	String query24HoursDataTrnIDByParams(Map<String, Object> args);
	
	//单条实时查询返现结果
	Education selectSingleResultByTrnId(String trnId);
}
