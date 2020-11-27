package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCustindexDao;
import com.huaxia.opas.domain.CRMSpIfsCustindex;
import com.huaxia.opas.mapper.crm.CRMSpIfsCustindexMapper;

@Repository
public class CRMSpIfsCustindexDaoImpl implements CRMSpIfsCustindexDao {
	
	@Resource
	private CRMSpIfsCustindexMapper spIfsCustindexMapper;

	@Override
	public int insert(CRMSpIfsCustindex spIfsCustindex) {
		return getSpIfsCustindexMapper().insert(spIfsCustindex);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCustindexMapper().insertBatch(parameters);
	}

	public CRMSpIfsCustindexMapper getSpIfsCustindexMapper() {
		return spIfsCustindexMapper;
	}

	public void setSpIfsCustindexMapper(CRMSpIfsCustindexMapper spIfsCustindexMapper) {
		this.spIfsCustindexMapper = spIfsCustindexMapper;
	}

}
