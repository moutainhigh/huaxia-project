package com.huaxia.opas.dao;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustInfo;

public interface CRMSpIfsCustInfoDao {

	int insert(CRMSpIfsCustInfo spIfsCustInfo);
	
	int insertBatch(Map<String, Object> parameters);
	
}
