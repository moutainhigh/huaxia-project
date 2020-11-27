package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCProfessionalDao;
import com.huaxia.opas.mapper.PBOCProfessionalMapper;

@Repository
public class PBOCProfessionalDaoImpl implements PBOCProfessionalDao {

	@Resource
	private PBOCProfessionalMapper professionalMapper;
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getProfessionalMapper().insertBatch(parameters);
	}

	public PBOCProfessionalMapper getProfessionalMapper() {
		return professionalMapper;
	}

	public void setProfessionalMapper(PBOCProfessionalMapper professionalMapper) {
		this.professionalMapper = professionalMapper;
	}

}
