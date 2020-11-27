package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustInfoLns;

public interface CRMSpIfsCustInfoLnsDao {

	int insert(CRMSpIfsCustInfoLns spIfsCustInfoLns);
	
	int insertBatch(Map<String, Object> parameters);
	
}
