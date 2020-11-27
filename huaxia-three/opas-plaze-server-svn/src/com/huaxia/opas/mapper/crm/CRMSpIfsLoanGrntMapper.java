package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanGrnt;

public interface CRMSpIfsLoanGrntMapper {

	int insert(CRMSpIfsLoanGrnt spIfsLoanGrnt);
	
	int insertBatch(Map<String, Object> parameters);
	
}
