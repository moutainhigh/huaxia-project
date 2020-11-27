package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCrcdPrycrt;

public interface CRMSpIfsCrcdPrycrtMapper {

	int insert(CRMSpIfsCrcdPrycrt spIfsCrcdPrycrt);
	
	int insertBatch(Map<String, Object> parameters);
	
}
