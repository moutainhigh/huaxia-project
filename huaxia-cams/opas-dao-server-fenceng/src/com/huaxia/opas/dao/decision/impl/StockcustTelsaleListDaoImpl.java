package com.huaxia.opas.dao.decision.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.StockcustTelsaleListDao;
import com.huaxia.opas.domain.thirdparty.StockcustTelsaleList;

public class StockcustTelsaleListDaoImpl extends AbstractDAO  implements StockcustTelsaleListDao  {
	private static final long serialVersionUID = 2757582418545513971L;
	
	private static final String NAMESPACES = "StockcustTelsaleList.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(StockcustTelsaleList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(StockcustTelsaleList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public StockcustTelsaleList selectByPrimaryKey(String autoId) {
		return (StockcustTelsaleList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(StockcustTelsaleList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(StockcustTelsaleList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}
   
}