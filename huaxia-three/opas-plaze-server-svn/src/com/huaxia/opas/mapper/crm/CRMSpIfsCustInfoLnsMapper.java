package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustInfoLns;

public interface CRMSpIfsCustInfoLnsMapper {

	int insert(CRMSpIfsCustInfoLns spIfsCustInfoLns);
	
	int insertBatch(Map<String, Object> parameters);
	
}
