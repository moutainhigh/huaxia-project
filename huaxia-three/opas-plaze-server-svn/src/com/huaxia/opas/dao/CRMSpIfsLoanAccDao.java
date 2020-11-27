package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanAcc;

public interface CRMSpIfsLoanAccDao {

	int insert(CRMSpIfsLoanAcc spIfsLoanAcc);
	
	int insertBatch(Map<String, Object> parameters);
	
}
