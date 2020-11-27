package com.huaxia.opas.mapper.crm;

import java.util.Map;

import com.huaxia.opas.domain.CRMSpIfsCustInfo;

public interface CRMSpIfsCustInfoMapper {

	int insert(CRMSpIfsCustInfo spIfsCustInfo);
	
	int insertBatch(Map<String, Object> parameters);
	
}
