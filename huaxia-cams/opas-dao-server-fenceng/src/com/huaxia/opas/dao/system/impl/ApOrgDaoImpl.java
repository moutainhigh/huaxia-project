package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApOrgDao;
import com.huaxia.opas.domain.system.ApOrg;

public class ApOrgDaoImpl extends AbstractDAO implements ApOrgDao {

	private static final long serialVersionUID = 6176099682506657685L;

	private static final String NAMESPACES = "ApOrg.";

	@Override
	public int insertApOrg(ApOrg apOrg) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApOrg", apOrg);
	}

	@Override
	public int insertApOrgSelective(ApOrg apOrg) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApOrgSelective", apOrg);
	}

	@Override
	public int deleteApOrgByOrgId(String orgId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApOrgByOrgId", orgId);
	}

	@Override
	public int updateApOrg(ApOrg apOrg) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApOrg", apOrg);
	}

	@Override
	public int updateApOrgSelective(ApOrg apOrg) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApOrgSelective", apOrg);
	}

	@Override
	public ApOrg queryApOrgByOrgId(String orgId) throws CoreException {
		return (ApOrg) (getSqlMap().queryForObject(NAMESPACES + "queryApOrgByOrgId", orgId));
	}

	@Override
	public List<ApOrg> queryOrgList(ApOrg apOrg, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryOrgList", apOrg, curNum, pageNum);
	}

	@Override
	public List<ApOrg> queryOrgListWithoutPage(ApOrg apOrg) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryOrgList", apOrg);
	}

	@Override
	public int queryOrgCount(ApOrg apOrg) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryOrgCount", apOrg);
	}

	@Override
	public List<ApOrg> queryUserOrgs(String userId) {
		return getSqlMap().queryForList(NAMESPACES + "queryUserOrg", userId);
	}

	/**
	 * 通过机构号查询机构条数
	 */
	@Override
	public int queryOrgCountByOrgNo(String orgNo) throws CoreException {

		return getSqlMap().queryForObject(NAMESPACES + "queryOrgCountByOrgNo", orgNo);
	}

	@Override
	public List<ApOrg> queryOrgByLevel() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryOrgByLevel");
	}
	
	//用户筛选条件  组别  shihuan  2017-04-14
	@Override
	public List<ApOrg> queryOrgForUser() {
		return getSqlMap().queryForList(NAMESPACES + "queryOrgForUser");
	}

	@Override
	public List<Map<String, String>> queryApOrg() throws CoreException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list = getSqlMap().queryForList(NAMESPACES + "queryApOrg");
		return list;

	}
	//添加时校验  机构名称不能相同  shihuan  2017-04-24 
	@Override
	public int queryOrgName(String orgName) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryOrgName");
	}
	
	@Override
	public ApOrg queryOrgByOrgNo(String orgNo) throws CoreException {
		return (ApOrg) (getSqlMap().queryForObject(NAMESPACES + "queryOrgByOrgNo", orgNo));
	}
	
	@Override
	public List<Map<String, Object>> queryOrgByOrgLevel(Map<String, Object> map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectOrgByOrgLevel", map);
	}
}