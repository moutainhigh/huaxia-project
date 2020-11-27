package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasEnterpriseNetworkDao;

public class OpasEnterpriseNetworkDaoImpl   extends AbstractDAO implements OpasEnterpriseNetworkDao,Serializable {

	@Override
	public List<Map<String,String>>  queryEnterpriseNetworkStatus(String appId) {
			return getSqlMap().queryForList("OpasEnterpriseNetwork.queryEpStatus", appId);
	}
	
}
