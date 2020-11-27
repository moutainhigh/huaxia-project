package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanDtl;

public interface CRMSpIfsLoanDtlDao {

	int insert(CRMSpIfsLoanDtl spIfsLoanDtl);
	
	int insertBatch(Map<String, Object> parameters);
	
}
