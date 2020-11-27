package com.huaxia.opas.mapper;

import java.util.Map;

public interface PBOCCreditDetailMapper {

	int insertBatchForLoan(Map<String, Object> parameters);

	int insertBatchForLoancard(Map<String, Object> parameters);
	
	int insertBatchForStandardLoancard(Map<String, Object> parameters);

}
