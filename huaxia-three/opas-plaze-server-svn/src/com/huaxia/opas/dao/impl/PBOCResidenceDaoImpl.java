package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCResidenceDao;
import com.huaxia.opas.mapper.PBOCResidenceMapper;

@Repository
public class PBOCResidenceDaoImpl implements PBOCResidenceDao {

	@Resource
	private PBOCResidenceMapper residenceMapper;
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getResidenceMapper().insertBatch(parameters);
	}

	public PBOCResidenceMapper getResidenceMapper() {
		return residenceMapper;
	}

	public void setResidenceMapper(PBOCResidenceMapper residenceMapper) {
		this.residenceMapper = residenceMapper;
	}

}
