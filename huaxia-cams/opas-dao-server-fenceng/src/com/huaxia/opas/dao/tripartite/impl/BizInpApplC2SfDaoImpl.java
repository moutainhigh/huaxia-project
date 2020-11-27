package com.huaxia.opas.dao.tripartite.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.tripartite.BizInpApplC2SfDao;

public class BizInpApplC2SfDaoImpl extends AbstractDAO implements BizInpApplC2SfDao {
	
	private static final long serialVersionUID = -9098700439841537247L;
	private static final String NAMESPACES = "BizInpApplC2Sf.";
	@Override
	public void updateCardC2SeaMemberId(Map<String, Object> params) {
		 getSqlMap().update(NAMESPACES + "updateCardC2SeaMemberId", params);
	}


}