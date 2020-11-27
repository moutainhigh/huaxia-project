package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCustindexAvgDao;
import com.huaxia.opas.domain.CRMSpIfsCustindexAvg;
import com.huaxia.opas.mapper.crm.CRMSpIfsCustindexAvgMapper;

@Repository
public class CRMSpIfsCustindexAvgDaoImpl implements CRMSpIfsCustindexAvgDao {

	@Resource
	private CRMSpIfsCustindexAvgMapper spIfsCustindexAvgMapper;
	
	@Override
	public int insert(CRMSpIfsCustindexAvg spIfsCustindexAvg) {
		return getSpIfsCustindexAvgMapper().insert(spIfsCustindexAvg);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCustindexAvgMapper().insertBatch(parameters);
	}

	public CRMSpIfsCustindexAvgMapper getSpIfsCustindexAvgMapper() {
		return spIfsCustindexAvgMapper;
	}

	public void setSpIfsCustindexAvgMapper(CRMSpIfsCustindexAvgMapper spIfsCustindexAvgMapper) {
		this.spIfsCustindexAvgMapper = spIfsCustindexAvgMapper;
	}

}
