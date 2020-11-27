package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TeamDao;
import com.huaxia.opas.domain.sysparm.TeamName;

public class TeamDaoImpl extends AbstractDAO implements TeamDao {


	private static final long serialVersionUID = 5590649230697386055L;
	
	private static final String NAMESPACES = "TeamName.";
	
	
	@Override
	public int queryTeamListCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTeamListCount", map);
	}

	@Override
	public List<TeamName> queryTeamList(Map<String, Object> map, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryTeamList", map, curNum, pageNum);
	}
	//根据autoId删除团办名单
	@Override
	public void deleteTeamNameByAutoId(String autoId) {
		getSqlMap().delete(NAMESPACES + "deleteTeamNameById", autoId);
		
	}
	@Override
	public int insert(TeamName record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(TeamName record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}
	
	@Override
	public int update(TeamName record) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTeamNameSelective", record);
	}

	@Override
	public void updateStatusById(TeamName record) {
		
		getSqlMap().update(NAMESPACES + "updateStatusById", record);
	}
	
	@Override
	public int delete(TeamName record) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TeamName selectById(TeamName t) {
		return getSqlMap().queryForObject(NAMESPACES + "selectById", t);
	}

	@Override
	public int showTeamNameHisCount(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "showTeamNameHisCount", autoId);
	}

	@Override
	public List<TeamName> showTeamNameHisList(String autoId, int curNum, int pageNum) {
		List<TeamName> list = new  ArrayList<TeamName>();
		list= getSqlMap().queryForList(NAMESPACES + "showTeamNameHisList",autoId,curNum,pageNum);
		return list;
	}


	@Override
	public void insertTeamHis(TeamName teamName) {
		getSqlMap().insert(NAMESPACES + "insertTeamHis", teamName);
	}

	@Override
	public int insertTeamNameFromFile(List<TeamName> list) {
		return getSqlMap().insert(NAMESPACES + "insertTeamNameFromFile", list);
	}


}
