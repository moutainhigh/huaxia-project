package com.huaxia.opas.dao.sysparm;

import java.util.List;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.DAO;
import com.huaxia.opas.domain.decision.TeamdealList;



/**
 * 名单库管理_易达金团办名单dao层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-19
 * 
 * @version 1.0
 */
public interface TeamdealDao extends DAO<TeamdealList>{
	
	/**
	 * 批量导入易达金团办名单实例（excel文件）
	 * 
	 * @author luzhen.ou
	 * 
	 * @param TeamdealList
	 * 
	 * @return Int
	 * */
	public int insertTeamdealList(List<TeamdealList> list) throws CoreException;
	
	/**
	 * 条件查询易达金团办名单的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param teamdealList
	 * 
	 * @return int
	 * */	
	public int queryTeamdealCount(TeamdealList teamdealList) throws CoreException;
	
	/**
	 * 条件查询易达金团办名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param TeamdealList teamdealList，int curNum, int pageNum
	 * 
	 * @return List<TeamdealList>
	 * */	
	public List<TeamdealList> queryTeamdealList(TeamdealList teamdealList, int curNum, int pageNum)throws CoreException;

	/**
	 * 批量设置易达金团办名单实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param teamdealList
	 * 
	 * @return int
	 * */	
	public int updateTeamdealActive(TeamdealList teamdealList)throws CoreException;

	/**
	 * 条件查询易达金团办名单实例历史操作的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param teamdealList
	 * 
	 * @return int
	 * */	
	public int queryTeamdealHistoryCount(TeamdealList teamdealList)throws CoreException;

	/**
	 * 查询，分页查询易达金团办名单实例历史操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param TeamdealList teamdealList，int curNum, int pageNum
	 * 
	 * @return List<TeamdealList>
	 * */	
	public List<TeamdealList> queryTeamdealHistoryList(TeamdealList teamdealList, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 新增易达金团办名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param TeamdealList
	 * 
	 * @return Int
	 * */
	public int insertTeamdealHistory(TeamdealList teamdealList) throws CoreException;
	
	/**
	 * 批量新增易达金团办名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param List<TeamdealList> list
	 * 
	 * @return int
	 * */
	public int insertTeamdealHistoryList(List<TeamdealList> list) throws CoreException;
	
	
	/**
	 * 新增易达金团办名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param TeamdealList
	 * 
	 * @return Int
	 * */
	int insert1(TeamdealList teamdealList) throws CoreException;

}
