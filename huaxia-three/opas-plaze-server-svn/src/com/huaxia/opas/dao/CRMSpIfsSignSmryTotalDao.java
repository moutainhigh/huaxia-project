package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsSignSmryTotal;

public interface CRMSpIfsSignSmryTotalDao {

	int insert(CRMSpIfsSignSmryTotal spIfsSignSmryTotal);
	
	int insertBatch(Map<String, Object> parameters);
	
}
