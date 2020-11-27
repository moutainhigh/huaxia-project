package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCrcdPrycrtDao;
import com.huaxia.opas.domain.CRMSpIfsCrcdPrycrt;
import com.huaxia.opas.mapper.crm.CRMSpIfsCrcdPrycrtMapper;

@Repository
public class CRMSpIfsCrcdPrycrtDaoImpl implements CRMSpIfsCrcdPrycrtDao {

	@Resource
	private CRMSpIfsCrcdPrycrtMapper spIfsCrcdPrycrtMapper;

	@Override
	public int insert(CRMSpIfsCrcdPrycrt spIfsCrcdPrycrt) {
		return getSpIfsCrcdPrycrtMapper().insert(spIfsCrcdPrycrt);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCrcdPrycrtMapper().insertBatch(parameters);
	}

	public CRMSpIfsCrcdPrycrtMapper getSpIfsCrcdPrycrtMapper() {
		return spIfsCrcdPrycrtMapper;
	}

	public void setSpIfsCrcdPrycrtMapper(CRMSpIfsCrcdPrycrtMapper spIfsCrcdPrycrtMapper) {
		this.spIfsCrcdPrycrtMapper = spIfsCrcdPrycrtMapper;
	}

}
