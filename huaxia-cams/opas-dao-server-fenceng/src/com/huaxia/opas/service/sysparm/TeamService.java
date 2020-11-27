package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.TeamName;
import com.huaxia.opas.service.BaseService;

public interface TeamService extends BaseService<TeamName>{

	public int queryTeamListCount(Map<String, Object> map);

	public List<TeamName> queryTeamList(Map<String, Object> map, int curNum, int pageNum);

	public void deleteTeamNameByAutoId(String autoId);

	public void NoTeamName(TeamName record) throws Exception;

	public void OkTeamName(TeamName record) throws Exception;

	public int insertTeamNameFromFile(List<TeamName> list, BatchfileInfo batchfileInfo) throws Exception;

	public int showTeamNameHisCount(String autoId);

	public List<TeamName> showTeamNameHisList(String autoId, int curNum, int pageNum);

	public int update(TeamName teamName) throws CoreException;
	
}
