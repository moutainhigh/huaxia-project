package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanAccAvg;

public interface CRMSpIfsLoanAccAvgDao {

	int insert(CRMSpIfsLoanAccAvg spIfsLoanAccAvg);
	
	int insertBatch(Map<String, Object> parameters);
	
}
