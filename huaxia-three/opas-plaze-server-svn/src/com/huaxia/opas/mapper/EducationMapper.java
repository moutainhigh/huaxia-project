package com.huaxia.opas.mapper;

import com.huaxia.opas.domain.Education;

public interface EducationMapper {

	int insert(Education education);
	
	int selectCountByAppId(String appId);
	
}
