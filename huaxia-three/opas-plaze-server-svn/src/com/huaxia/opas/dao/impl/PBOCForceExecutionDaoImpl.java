package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCForceExecutionDao;
import com.huaxia.opas.mapper.PBOCForceExecutionMapper;

@Repository
public class PBOCForceExecutionDaoImpl implements PBOCForceExecutionDao {

	@Resource
	private PBOCForceExecutionMapper forceExecutionMapper;

	public PBOCForceExecutionMapper getForceExecutionMapper() {
		return forceExecutionMapper;
	}

	public void setForceExecutionMapper(PBOCForceExecutionMapper forceExecutionMapper) {
		this.forceExecutionMapper = forceExecutionMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getForceExecutionMapper().insertBatch(parameters);
	}
	
}
