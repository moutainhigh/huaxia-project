package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCybkBase;

public interface CRMSpIfsCybkBaseMapper {

	int insert(CRMSpIfsCybkBase spIfsCybkBase);
	
	int insertBatch(Map<String, Object> parameters);
	
}
