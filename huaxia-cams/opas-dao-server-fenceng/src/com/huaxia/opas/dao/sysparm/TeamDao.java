package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.DAO;
import com.huaxia.opas.domain.sysparm.TeamName;

public interface TeamDao extends DAO<TeamName> {

	int insert(TeamName record);

	int insertSelective(TeamName record);

	int queryTeamListCount(Map<String, Object> map);

	List<TeamName> queryTeamList(Map<String, Object> map, int curNum, int pageNum);

	void deleteTeamNameByAutoId(String autoId);

	void updateStatusById(TeamName record);

	int showTeamNameHisCount(String autoId);

	List<TeamName> showTeamNameHisList(String autoId, int curNum, int pageNum);

	TeamName  selectById(TeamName t);

	void insertTeamHis(TeamName teamName);
	
	public int update(TeamName t) throws CoreException;

	int insertTeamNameFromFile(List<TeamName> list);
}