package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustCard;

public interface CRMSpIfsCustCardMapper {

	int insert(CRMSpIfsCustCard spIfsCustCard);
	
	int insertBatch(Map<String, Object> parameters);
	
}
