package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanAcc;

public interface CRMSpIfsLoanAccMapper {

	int insert(CRMSpIfsLoanAcc spIfsLoanAcc);
	
	int insertBatch(Map<String, Object> parameters);
	
}
