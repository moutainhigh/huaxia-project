package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustDebt;

public interface CRMSpIfsCustDebtDao {

	int insert(CRMSpIfsCustDebt spIfsCustDebt);
	
	int insertBatch(Map<String, Object> parameters);
	
}
