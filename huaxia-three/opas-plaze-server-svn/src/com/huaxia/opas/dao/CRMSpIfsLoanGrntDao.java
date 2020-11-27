package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanGrnt;

public interface CRMSpIfsLoanGrntDao {

	int insert(CRMSpIfsLoanGrnt spIfsLoanGrnt);
	
	int insertBatch(Map<String, Object> parameters);
	
}
