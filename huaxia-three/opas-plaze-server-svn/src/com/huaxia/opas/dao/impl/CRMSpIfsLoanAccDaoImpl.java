package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsLoanAccDao;
import com.huaxia.opas.domain.CRMSpIfsLoanAcc;
import com.huaxia.opas.mapper.crm.CRMSpIfsLoanAccMapper;

@Repository
public class CRMSpIfsLoanAccDaoImpl implements CRMSpIfsLoanAccDao {
	
	@Resource
	private CRMSpIfsLoanAccMapper spIfsLoanAccMapper;

	@Override
	public int insert(CRMSpIfsLoanAcc spIfsLoanAcc) {
		return getSpIfsLoanAccMapper().insert(spIfsLoanAcc);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsLoanAccMapper().insertBatch(parameters);
	}

	public CRMSpIfsLoanAccMapper getSpIfsLoanAccMapper() {
		return spIfsLoanAccMapper;
	}

	public void setSpIfsLoanAccMapper(CRMSpIfsLoanAccMapper spIfsLoanAccMapper) {
		this.spIfsLoanAccMapper = spIfsLoanAccMapper;
	}

}
