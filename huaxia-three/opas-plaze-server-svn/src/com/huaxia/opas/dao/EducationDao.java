package com.huaxia.opas.dao;

import com.huaxia.opas.domain.Education;

public interface EducationDao {

	int insert(Education education);
	
	int selectCountByAppId(String appId);
	
}
