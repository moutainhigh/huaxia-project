package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsSignCybkDao;
import com.huaxia.opas.domain.CRMSpIfsSignCybk;
import com.huaxia.opas.mapper.crm.CRMSpIfsSignCybkMapper;

@Repository
public class CRMSpIfsSignCybkDaoImpl implements CRMSpIfsSignCybkDao {
	
	@Resource
	private CRMSpIfsSignCybkMapper spIfsSignCybkMapper;

	@Override
	public int insert(CRMSpIfsSignCybk spIfsSignCybk) {
		return getSpIfsSignCybkMapper().insert(spIfsSignCybk);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsSignCybkMapper().insertBatch(parameters);
	}

	public CRMSpIfsSignCybkMapper getSpIfsSignCybkMapper() {
		return spIfsSignCybkMapper;
	}

	public void setSpIfsSignCybkMapper(CRMSpIfsSignCybkMapper spIfsSignCybkMapper) {
		this.spIfsSignCybkMapper = spIfsSignCybkMapper;
	}

}
