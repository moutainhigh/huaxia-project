package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.GoodcompanyListDao;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;
import com.huaxia.opas.domain.sysparm.MajorschoolList;

public class GoodcompanyListDaoImpl extends AbstractDAO implements GoodcompanyListDao {
	
	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "GoodcompanyList.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(GoodcompanyList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(GoodcompanyList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public GoodcompanyList selectByPrimaryKey(String autoId) {
		return (GoodcompanyList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public List<GoodcompanyList> selectByCondtion(Map map,int curNum,int pageNum) {
		return (getSqlMap().queryForList(NAMESPACES + "selectByCondtion", map,curNum,pageNum));
	}
	@Override
	public int updateByPrimaryKeySelective(GoodcompanyList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(GoodcompanyList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryCountList(Map map) {
		return (getSqlMap().queryForObject(NAMESPACES + "queryCountList", map));
	}

}