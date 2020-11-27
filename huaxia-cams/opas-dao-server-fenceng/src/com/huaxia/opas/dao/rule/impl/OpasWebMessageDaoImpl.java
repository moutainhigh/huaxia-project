package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasWebMessageDao;

public class OpasWebMessageDaoImpl   extends AbstractDAO implements OpasWebMessageDao,Serializable {

	private static final long serialVersionUID = -8012539813040031798L;

	@Override
	public List<Map<String,Object>>  queryWebCustomerBehaviorInfoByAppId(String appId) {
			return getSqlMap().queryForList("OpasWebMessage.queryWebCustomerBehaviorInfoByAppId", appId);
	}
	
}
