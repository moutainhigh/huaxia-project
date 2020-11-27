package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.TargetcompanyListDao;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;

public class TargetcompanyListDaoImpl extends AbstractDAO implements TargetcompanyListDao {

	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "TargetcompanyList.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(TargetcompanyList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(TargetcompanyList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public TargetcompanyList selectByPrimaryKey(String autoId) {
		return (TargetcompanyList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public int queryCountList(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountList", map);
	}
	@Override
	public List<TargetcompanyList> selectByCondtion(Map map,int curNum,int pageNum) {
		return (getSqlMap().queryForList(NAMESPACES + "selectByCondtion", map,curNum,pageNum));
	}
	@Override
	public int updateByPrimaryKeySelective(TargetcompanyList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(TargetcompanyList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	

}