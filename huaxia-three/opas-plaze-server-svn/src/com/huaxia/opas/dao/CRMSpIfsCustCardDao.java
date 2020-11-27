package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustCard;

public interface CRMSpIfsCustCardDao {

	int insert(CRMSpIfsCustCard spIfsCustCard);
	
	int insertBatch(Map<String, Object> parameters);
	
}
