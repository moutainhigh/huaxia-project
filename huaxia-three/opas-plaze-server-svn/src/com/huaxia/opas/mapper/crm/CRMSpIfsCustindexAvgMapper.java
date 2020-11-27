package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustindexAvg;

public interface CRMSpIfsCustindexAvgMapper {

	int insert(CRMSpIfsCustindexAvg spIfsCustindexAvg);
	
	int insertBatch(Map<String, Object> parameters);
	
}
