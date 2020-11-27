package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasUniAddrDao;
import com.huaxia.opas.dao.rule.OpasYuShenDao;

public class OpasUniAddrDaoImpl   extends AbstractDAO implements OpasUniAddrDao,Serializable {

	private static final long serialVersionUID = -3245415864183128920L;

	@Override
	public List<Map<String,Object>>  queryUniAddrInforByAppId(String appId) {
			return getSqlMap().queryForList("OpasUniAddrInfo.queryUniAddrInforByAppId", appId);
	}
	
}
