package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsLoanDtlDao;
import com.huaxia.opas.domain.CRMSpIfsLoanDtl;
import com.huaxia.opas.mapper.crm.CRMSpIfsLoanDtlMapper;

@Repository
public class CRMSpIfsLoanDtlDaoImpl implements CRMSpIfsLoanDtlDao {
	
	@Resource
	private CRMSpIfsLoanDtlMapper spIfsLoanDtlMapper;

	@Override
	public int insert(CRMSpIfsLoanDtl spIfsLoanDtl) {
		return getSpIfsLoanDtlMapper().insert(spIfsLoanDtl);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsLoanDtlMapper().insertBatch(parameters);
	}

	public CRMSpIfsLoanDtlMapper getSpIfsLoanDtlMapper() {
		return spIfsLoanDtlMapper;
	}

	public void setSpIfsLoanDtlMapper(CRMSpIfsLoanDtlMapper spIfsLoanDtlMapper) {
		this.spIfsLoanDtlMapper = spIfsLoanDtlMapper;
	}

}
