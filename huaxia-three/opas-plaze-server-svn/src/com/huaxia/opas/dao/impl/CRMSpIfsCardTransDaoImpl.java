package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCardTransDao;
import com.huaxia.opas.domain.CRMSpIfsCardTrans;
import com.huaxia.opas.mapper.crm.CRMSpIfsCardTransMapper;

@Repository
public class CRMSpIfsCardTransDaoImpl implements CRMSpIfsCardTransDao {
	
	@Resource
	private CRMSpIfsCardTransMapper spIfsCardTransMapper;

	@Override
	public int insert(CRMSpIfsCardTrans spIfsCardTrans) {
		return getSpIfsCardTransMapper().insert(spIfsCardTrans);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCardTransMapper().insertBatch(parameters);
	}

	public CRMSpIfsCardTransMapper getSpIfsCardTransMapper() {
		return spIfsCardTransMapper;
	}

	public void setSpIfsCardTransMapper(CRMSpIfsCardTransMapper spIfsCardTransMapper) {
		this.spIfsCardTransMapper = spIfsCardTransMapper;
	}

}
