package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasYuShenDao;

public class OpasYuShenDaoImpl   extends AbstractDAO implements OpasYuShenDao,Serializable {

	private static final long serialVersionUID = 4279304041727557900L;

	@Override
	public List<Map<String,String>>  queryYuShenInfoByAppId(String appId) {
			return getSqlMap().queryForList("OpasYuShenInfo.queryYuShenInfoByAppId", appId);
	}
	
}
