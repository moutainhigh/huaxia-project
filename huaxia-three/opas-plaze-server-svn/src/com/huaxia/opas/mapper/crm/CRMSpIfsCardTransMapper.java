package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCardTrans;

public interface CRMSpIfsCardTransMapper {

	int insert(CRMSpIfsCardTrans spIfsCardTrans);
	
	int insertBatch(Map<String, Object> parameters);
	
}
