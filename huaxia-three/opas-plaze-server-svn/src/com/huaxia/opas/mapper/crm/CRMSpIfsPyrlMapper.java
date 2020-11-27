package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsPyrl;

public interface CRMSpIfsPyrlMapper {

	int insert(CRMSpIfsPyrl spIfsPyrl);
	
	int insertBatch(Map<String, Object> parameters);
	
}
