package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanDtl;

public interface CRMSpIfsLoanDtlMapper {

	int insert(CRMSpIfsLoanDtl spIfsLoanDtl);
	
	int insertBatch(Map<String, Object> parameters);
	
}
