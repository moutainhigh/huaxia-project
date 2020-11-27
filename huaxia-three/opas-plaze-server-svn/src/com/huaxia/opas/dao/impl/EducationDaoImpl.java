package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.EducationDao;
import com.huaxia.opas.domain.Education;
import com.huaxia.opas.mapper.EducationMapper;

@Repository
public class EducationDaoImpl implements EducationDao {
	
	@Resource
	private EducationMapper educationMapper;

	@Override
	public int insert(Education education) {
		return getEducationMapper().insert(education);
	}
	
	@Override
	public int selectCountByAppId(String appId) {
		return getEducationMapper().selectCountByAppId(appId);
	}

	public EducationMapper getEducationMapper() {
		return educationMapper;
	}

	public void setEducationMapper(EducationMapper educationMapper) {
		this.educationMapper = educationMapper;
	}

}
