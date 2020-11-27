package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsPyrl;

public interface CRMSpIfsPyrlDao {

	int insert(CRMSpIfsPyrl spIfsPyrl);
	
	int insertBatch(Map<String, Object> parameters);
	
}
