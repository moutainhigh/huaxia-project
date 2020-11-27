package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsPyrlDao;
import com.huaxia.opas.domain.CRMSpIfsPyrl;
import com.huaxia.opas.mapper.crm.CRMSpIfsPyrlMapper;

@Repository
public class CRMSpIfsPyrlDaoImpl implements CRMSpIfsPyrlDao {
	
	@Resource
	private CRMSpIfsPyrlMapper spIfsPyrlMapper;

	@Override
	public int insert(CRMSpIfsPyrl spIfsPyrl) {
		return getSpIfsPyrlMapper().insert(spIfsPyrl);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsPyrlMapper().insertBatch(parameters);
	}

	public CRMSpIfsPyrlMapper getSpIfsPyrlMapper() {
		return spIfsPyrlMapper;
	}

	public void setSpIfsPyrlMapper(CRMSpIfsPyrlMapper spIfsPyrlMapper) {
		this.spIfsPyrlMapper = spIfsPyrlMapper;
	}

}
