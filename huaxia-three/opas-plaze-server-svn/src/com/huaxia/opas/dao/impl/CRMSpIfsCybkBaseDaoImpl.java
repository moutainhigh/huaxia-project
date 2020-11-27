package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCybkBaseDao;
import com.huaxia.opas.domain.CRMSpIfsCybkBase;
import com.huaxia.opas.mapper.crm.CRMSpIfsCybkBaseMapper;

@Repository
public class CRMSpIfsCybkBaseDaoImpl implements CRMSpIfsCybkBaseDao {

	@Resource
	private CRMSpIfsCybkBaseMapper spIfsCybkBaseMapper;
	
	@Override
	public int insert(CRMSpIfsCybkBase spIfsCybkBase) {
		return getSpIfsCybkBaseMapper().insert(spIfsCybkBase);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCybkBaseMapper().insertBatch(parameters);
	}

	public CRMSpIfsCybkBaseMapper getSpIfsCybkBaseMapper() {
		return spIfsCybkBaseMapper;
	}

	public void setSpIfsCybkBaseMapper(CRMSpIfsCybkBaseMapper spIfsCybkBaseMapper) {
		this.spIfsCybkBaseMapper = spIfsCybkBaseMapper;
	}

}
