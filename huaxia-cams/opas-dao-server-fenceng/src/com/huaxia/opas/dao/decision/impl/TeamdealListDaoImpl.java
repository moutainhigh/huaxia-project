package com.huaxia.opas.dao.decision.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.TeamdealListDao;
import com.huaxia.opas.domain.decision.TeamdealList;

public class TeamdealListDaoImpl extends AbstractDAO implements TeamdealListDao {

	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "TeamdealList.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(TeamdealList record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(TeamdealList record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public TeamdealList selectByPrimaryKey(String autoId) {
		return (TeamdealList) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}
	@Override
	public List<TeamdealList> selectByCondtion(Map map,int curNum,int pageNum) {
		return (getSqlMap().queryForList(NAMESPACES + "selectByCondtion", map,curNum,pageNum));
	}
	@Override
	public int updateByPrimaryKeySelective(TeamdealList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(TeamdealList record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(TeamdealList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int queryCountList(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryCountList", map);
	}

}