package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustindexAvg;

public interface CRMSpIfsCustindexAvgDao {

	int insert(CRMSpIfsCustindexAvg spIfsCustindexAvg);
	
	int insertBatch(Map<String, Object> parameters);
	
}
