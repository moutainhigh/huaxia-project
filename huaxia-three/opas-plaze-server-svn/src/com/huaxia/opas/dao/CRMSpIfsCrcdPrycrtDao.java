package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCrcdPrycrt;

public interface CRMSpIfsCrcdPrycrtDao {

	int insert(CRMSpIfsCrcdPrycrt spIfsCrcdPrycrt);
	
	int insertBatch(Map<String, Object> parameters);
	
}
