package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCustDebtDao;
import com.huaxia.opas.domain.CRMSpIfsCustDebt;
import com.huaxia.opas.mapper.crm.CRMSpIfsCustDebtMapper;

@Repository
public class CRMSpIfsCustDebtDaoImpl implements CRMSpIfsCustDebtDao {

	@Resource
	private CRMSpIfsCustDebtMapper spIfsCustDebtMapper;

	@Override
	public int insert(CRMSpIfsCustDebt spIfsCustDebt) {
		return getSpIfsCustDebtMapper().insert(spIfsCustDebt);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCustDebtMapper().insertBatch(parameters);
	}

	public CRMSpIfsCustDebtMapper getSpIfsCustDebtMapper() {
		return spIfsCustDebtMapper;
	}

	public void setSpIfsCustDebtMapper(CRMSpIfsCustDebtMapper spIfsCustDebtMapper) {
		this.spIfsCustDebtMapper = spIfsCustDebtMapper;
	}

}
