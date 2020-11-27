package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCardTrans;

public interface CRMSpIfsCardTransDao {

	int insert(CRMSpIfsCardTrans spIfsCardTrans);
	
	int insertBatch(Map<String, Object> parameters);
	
}
