package com.huaxia.opas.dao.tripartite.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.tripartite.BizInpApplC1SfDao;

public class BizInpApplC1SfDaoImpl extends AbstractDAO implements BizInpApplC1SfDao {

	private static final long serialVersionUID = 317156888545328900L;
	private static final String NAMESPACES = "BizInpApplC1Sf.";
	@Override
	public void updateCardC1SeaMemberId(Map<String, Object> params) {
		 getSqlMap().update(NAMESPACES + "updateCardC1SeaMemberId", params);
	}

}