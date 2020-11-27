package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.ApplyLifeCicleDao;
import com.huaxia.opas.domain.ApplyLifeCicle;
import com.huaxia.opas.mapper.ApplyLifeCicleMapper;

@Repository
public class ApplyLifeCicleDaoImpl implements ApplyLifeCicleDao {

	@Resource
	private ApplyLifeCicleMapper applyLifeCicleMapper;
	
	public ApplyLifeCicleMapper getApplyLifeCicleMapper() {
		return applyLifeCicleMapper;
	}

	public void setApplyLifeCicleMapper(ApplyLifeCicleMapper applyLifeCicleMapper) {
		this.applyLifeCicleMapper = applyLifeCicleMapper;
	}

	@Override
	public void insertApplyLifeCicle(ApplyLifeCicle applyLifeCicle) {
		applyLifeCicleMapper.insertApplyLifeCicle(applyLifeCicle);
	}

}