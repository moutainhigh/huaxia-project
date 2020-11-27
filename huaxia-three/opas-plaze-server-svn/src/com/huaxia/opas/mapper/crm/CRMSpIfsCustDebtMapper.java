package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustDebt;

public interface CRMSpIfsCustDebtMapper {

	int insert(CRMSpIfsCustDebt spIfsCustDebt);
	
	int insertBatch(Map<String, Object> parameters);
	
}
