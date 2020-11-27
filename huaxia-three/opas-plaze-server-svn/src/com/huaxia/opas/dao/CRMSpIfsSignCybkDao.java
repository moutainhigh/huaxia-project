package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsSignCybk;

public interface CRMSpIfsSignCybkDao {

	int insert(CRMSpIfsSignCybk spIfsSignCybk);
	
	int insertBatch(Map<String, Object> parameters);
	
}
