package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCustInfoLnsDao;
import com.huaxia.opas.domain.CRMSpIfsCustInfoLns;
import com.huaxia.opas.mapper.crm.CRMSpIfsCustInfoLnsMapper;

@Repository
public class CRMSpIfsCustInfoLnsDaoImpl implements CRMSpIfsCustInfoLnsDao {
	
	@Resource
	private CRMSpIfsCustInfoLnsMapper spIfsCustInfoLnsMapper;

	@Override
	public int insert(CRMSpIfsCustInfoLns spIfsCustInfoLns) {
		return getSpIfsCustInfoLnsMapper().insert(spIfsCustInfoLns);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCustInfoLnsMapper().insertBatch(parameters);
	}

	public CRMSpIfsCustInfoLnsMapper getSpIfsCustInfoLnsMapper() {
		return spIfsCustInfoLnsMapper;
	}

	public void setSpIfsCustInfoLnsMapper(CRMSpIfsCustInfoLnsMapper spIfsCustInfoLnsMapper) {
		this.spIfsCustInfoLnsMapper = spIfsCustInfoLnsMapper;
	}

}
