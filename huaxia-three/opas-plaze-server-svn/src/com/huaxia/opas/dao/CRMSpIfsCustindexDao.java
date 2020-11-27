package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustindex;

public interface CRMSpIfsCustindexDao {

	int insert(CRMSpIfsCustindex spIfsCustindex);
	
	int insertBatch(Map<String, Object> parameters);
	
}
