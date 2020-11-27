package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustindex;

public interface CRMSpIfsCustindexMapper {

	int insert(CRMSpIfsCustindex spIfsCustindex);
	
	int insertBatch(Map<String, Object> parameters);
	
}
