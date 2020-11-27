package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCCompetenceDao;
import com.huaxia.opas.mapper.PBOCCompetenceMapper;

@Repository
public class PBOCCompetenceDaoImpl implements PBOCCompetenceDao {

	@Resource
	private PBOCCompetenceMapper competenceMapper;

	public PBOCCompetenceMapper getCompetenceMapper() {
		return competenceMapper;
	}

	public void setCompetenceMapper(PBOCCompetenceMapper competenceMapper) {
		this.competenceMapper = competenceMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getCompetenceMapper().insertBatch(parameters);
	}
	
}
