package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.TeamdealDao;
import com.huaxia.opas.domain.decision.TeamdealList;


/**
 *名单库管理_易达金团办名单dao层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-3-19
 * 
 * @version 1.0
 */
public class TeamdealDaoImpl extends AbstractDAO implements TeamdealDao {
	
	private static final long serialVersionUID = 7592372751814232904L;
	
	private static final String NAMESPACES = "TeamdealList.";

	@Override
	public int queryTeamdealCount(TeamdealList teamdealList) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryTeamdealCount", teamdealList);
	}

	@Override
	public List<TeamdealList> queryTeamdealList(TeamdealList teamdealList, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryTeamdealList", teamdealList, curNum, pageNum);
	}

	@Override
	public int updateTeamdealActive(TeamdealList teamdealList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTeamdealActive", teamdealList);
	}

	@Override
	public int queryTeamdealHistoryCount(TeamdealList teamdealList) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTeamdealHistoryCount", teamdealList);
	}

	@Override
	public List<TeamdealList> queryTeamdealHistoryList(TeamdealList teamdealList, int curNum, int pageNum){
		return getSqlMap().queryForList(NAMESPACES + "queryTeamdealHistoryList", teamdealList, curNum, pageNum);
	}

	@Override
	public int insertTeamdealList(List<TeamdealList> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTeamdealList", list);
	}
	
	@Override
	public int insertTeamdealHistory(TeamdealList teamdealList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTeamdealHistory", teamdealList);
	}

	@Override
	public int insertTeamdealHistoryList(List<TeamdealList> list)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertTeamdealHistoryList", list);
	}
	
	@Override
	public int insert1(TeamdealList teamdealList) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert1", teamdealList);
	}

	/**
	 * 修改易达金团办名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param TeamdealList
	 * 
	 * @return Int
	 * */	
	@Override
	public int update(TeamdealList teamdealList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "update", teamdealList);
	}
	
	/**
	 * 删除易达金团办名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param teamdealList
	 * 
	 * @return int
	 * */
	@Override
	public int delete(TeamdealList teamdealList) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "delete", teamdealList);
	}

	
	/**
	 * 查询易达金团办名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param String autoId
	 * 
	 * @return TeamdealList
	 * */	
	@Override
	public TeamdealList selectById(TeamdealList teamdealList) {
		return (TeamdealList)getSqlMap().queryForObject(NAMESPACES + "selectById", teamdealList);
	}

	@Override
	public int insert(TeamdealList t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
