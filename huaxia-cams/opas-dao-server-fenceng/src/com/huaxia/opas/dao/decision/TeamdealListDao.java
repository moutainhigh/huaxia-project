package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.decision.TeamdealList;

public interface TeamdealListDao {

	int deleteByPrimaryKey(String autoId);

	int insert(TeamdealList record);

	int insertSelective(TeamdealList record);

	TeamdealList selectByPrimaryKey(String autoId);

	int updateByPrimaryKeySelective(TeamdealList record);

	int updateByPrimaryKeyWithBLOBs(TeamdealList record);

	int updateByPrimaryKey(TeamdealList record);

	List<TeamdealList> selectByCondtion(Map map,int curNum,int pageNum);
	int queryCountList(Map map);
}