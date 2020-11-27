package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCybkBase;

public interface CRMSpIfsCybkBaseDao {

	int insert(CRMSpIfsCybkBase spIfsCybkBase);
	
	int insertBatch(Map<String, Object> parameters);
	
}
