package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.IdentityRiskDao;
import com.huaxia.opas.domain.sysparm.IdentityRisk;
/**
 * 系统参数--身份类风险名单dao层实现类的相关方法
 * 
 * @author luzhen.ou
 * 
 * @since 2017-3-10
 * 
 * @version 1.0
 */
public class IdentityRiskDaoImpl extends AbstractDAO implements IdentityRiskDao {

	private static final long serialVersionUID = 1062308779223148331L;

	private static final String NAMESPACES = "IdentityRisk.";

	@Override
	public int updateIdentityRisk(IdentityRisk identityRisk) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateIdentityRisk", identityRisk);
	}

	@Override
	public int queryIdentityRiskCount(IdentityRisk identityRisk) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryIdentityRiskCount", identityRisk);
	}

	@Override
	public List<IdentityRisk> queryIdentityRiskList(IdentityRisk identityRisk, int curNum, int pageNum) throws CoreException{
		return getSqlMap().queryForList(NAMESPACES + "queryIdentityRiskList", identityRisk, curNum, pageNum);
	}

	@Override
	public int deleteIdentityRisk(IdentityRisk identityRisk) throws CoreException{
		return getSqlMap().delete(NAMESPACES + "deleteIdentityRisk", identityRisk);
	}

	@Override
	public IdentityRisk queryIdentityRisk(String autoId) {
		return (IdentityRisk)getSqlMap().queryForObject(NAMESPACES + "queryIdentityRisk", autoId);
	}

	@Override
	public int updateIdentityRiskActive(IdentityRisk identityRisk) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateIdentityRiskActive", identityRisk);
	}

	@Override
	public int queryIdentityRiskHistoryCount(IdentityRisk identityRisk) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryIdentityRiskHistoryCount", identityRisk);
	}

	@Override
	public List<IdentityRisk> queryIdentityRiskHistoryList(IdentityRisk identityRisk, int curNum, int pageNum){
		return getSqlMap().queryForList(NAMESPACES + "queryIdentityRiskHistoryList", identityRisk, curNum, pageNum);
	}
	
	@Override
	public int insertIdentityRisk(IdentityRisk identityRisk) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertIdentityRisk", identityRisk);
	}

	@Override
	public int insertIdentityRiskList(List<IdentityRisk> list) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertIdentityRiskList", list);
	}
	
	@Override
	public int insertIdentityRiskHistory(IdentityRisk identityRisk) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertIdentityRiskHistory", identityRisk);
	}

	@Override
	public int insertIdentityRiskHistoryList(List<IdentityRisk> resutIdentitylist)  throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertIdentityRiskHistoryList", resutIdentitylist);
	}
	
}
