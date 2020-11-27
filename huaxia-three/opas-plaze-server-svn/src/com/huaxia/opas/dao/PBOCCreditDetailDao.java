package com.huaxia.opas.dao;

import java.util.Map;

public interface PBOCCreditDetailDao {
	
	int insertBatchForLoan(Map<String, Object> parameters);
	
	int insertBatchForLoancard(Map<String, Object> parameters);
	
	int insertBatchForStandardLoancard(Map<String, Object> parameters);
 
}
