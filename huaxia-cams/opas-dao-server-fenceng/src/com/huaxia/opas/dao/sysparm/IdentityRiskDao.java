package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.IdentityRisk;



/**
 * 系统参数--身份类风险名单dao层接口的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-10
 * 
 * @version 1.0
 */
public interface IdentityRiskDao {
	
	/**
	 * 新增身份类风险名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk
	 * 
	 * @return Int
	 * */
	public int insertIdentityRisk(IdentityRisk identityRisk) throws CoreException;
	
	/**
	 * 批量导入身份类风险名单实例（excel文件）
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk
	 * 
	 * @return Int
	 * */
	public int insertIdentityRiskList(List<IdentityRisk> list) throws CoreException;
	
	/**
	 * 查询身份类风险名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param String autoId
	 * 
	 * @return IdentityRisk
	 * */	
	public IdentityRisk queryIdentityRisk(String autoId);	
	/**
	 * 修改身份类风险名单实例
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk
	 * 
	 * @return Int
	 * */
	public int updateIdentityRisk(IdentityRisk identityRisk) throws CoreException;
	
	/**
	 * 条件查询身份类风险名单的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param identityRisk
	 * 
	 * @return int
	 * */	
	public int queryIdentityRiskCount(IdentityRisk identityRisk) throws CoreException;
	
	/**
	 * 条件查询身份类风险名单，分页查询
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk identityRisk，int curNum, int pageNum
	 * 
	 * @return List<IdentityRisk>
	 * */	
	public List<IdentityRisk> queryIdentityRiskList(IdentityRisk identityRisk, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 删除身份类风险名单实例通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param identityRisk
	 * 
	 * @return int
	 * */	
	public int deleteIdentityRisk(IdentityRisk identityRisk)throws CoreException;

	/**
	 * 批量设置身份类风险实例启用或禁用通过主键ids
	 * 
	 * @author luzhen.ou
	 * 
	 * @param identityRisk
	 * 
	 * @return int
	 * */	
	public int updateIdentityRiskActive(IdentityRisk identityRisk)throws CoreException;

	/**
	 * 查询身份类风险名单实例操作的总记录数，供分页使用
	 * 
	 * @author luzhen.ou
	 * 
	 * @param identityRisk
	 * 
	 * @return int
	 * */	
	public int queryIdentityRiskHistoryCount(IdentityRisk identityRisk)throws CoreException;

	/**
	 * 分页查询，分页查询身份类风险名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk identityRisk，int curNum, int pageNum
	 * 
	 * @return List<IdentityRisk>
	 * */	
	public List<IdentityRisk> queryIdentityRiskHistoryList(IdentityRisk identityRisk, int curNum, int pageNum)throws CoreException;
	
	/**
	 * 新增身份类风险名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param IdentityRisk
	 * 
	 * @return Int
	 * */
	public int insertIdentityRiskHistory(IdentityRisk identityRisk) throws CoreException;
	
	/**
	 * 批量新增身份类风险名单实例操作历史记录
	 * 
	 * @author luzhen.ou
	 * 
	 * @param List<IdentityRisk> list
	 * 
	 * @return int
	 * */
	public int insertIdentityRiskHistoryList(List<IdentityRisk> list) throws CoreException;

}
