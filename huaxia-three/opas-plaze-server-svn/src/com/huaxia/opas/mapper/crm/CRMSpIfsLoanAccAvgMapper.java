package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsLoanAccAvg;

public interface CRMSpIfsLoanAccAvgMapper {

	int insert(CRMSpIfsLoanAccAvg spIfsLoanAccAvg);
	
	int insertBatch(Map<String, Object> parameters);
	
}
