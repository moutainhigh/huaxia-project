package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsSignCybk;

public interface CRMSpIfsSignCybkMapper {

	int insert(CRMSpIfsSignCybk spIfsSignCybk);
	
	int insertBatch(Map<String, Object> parameters);
	
}
