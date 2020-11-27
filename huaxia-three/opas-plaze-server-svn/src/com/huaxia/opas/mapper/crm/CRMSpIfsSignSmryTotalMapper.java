package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsSignSmryTotal;

public interface CRMSpIfsSignSmryTotalMapper {

	int insert(CRMSpIfsSignSmryTotal spIfsSignSmryTotal);
	
	int insertBatch(Map<String, Object> parameters);
	
}
