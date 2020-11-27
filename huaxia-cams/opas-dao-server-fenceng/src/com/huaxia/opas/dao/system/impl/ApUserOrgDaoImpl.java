package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApUserOrgDao;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;

public class ApUserOrgDaoImpl extends AbstractDAO implements ApUserOrgDao {

	private static final long serialVersionUID = 5757582418545513970L;

	private static final String NAMESPACES = "ApUserOrg.";

	@Override
	public int insertApUserOrg(ApUserOrg apUserOrg) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApUserOrg", apUserOrg);
	}

	@Override
	public int insertApUserOrgSelective(ApUserOrg apUserOrg) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApUserOrgSelective", apUserOrg);
	}

	@Override
	public int deleteApUserOrgByOrgIdAndUserId(String orgId, String userId) throws CoreException {
		ApUserOrg apUserOrg = new ApUserOrg();
		apUserOrg.setOrgId(orgId);
		apUserOrg.setUserId(userId);
		return getSqlMap().delete(NAMESPACES + "deleteApUserOrgByOrgIdAndUserId", apUserOrg);
	}

	@Override
	public int updateApUserOrg(ApUserOrg apUserOrg) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApUserOrg", apUserOrg);
	}

	@Override
	public int updateApUserOrgSelective(ApUserOrg apUserOrg) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApUserOrgSelective", apUserOrg);
	}

	@Override
	public ApUserOrg queryApUserOrgByOrgIdAndUserId(String orgId, String userId) throws CoreException {
		ApUserOrg apUserOrg = new ApUserOrg();
		apUserOrg.setOrgId(orgId);
		apUserOrg.setUserId(userId);
		return (ApUserOrg) (getSqlMap().queryForObject(NAMESPACES + "queryApUserOrgByOrgIdAndUserId", apUserOrg));
	}

	@Override
	public List<ApUserOrg> queryApUserOrgByUserId(String userId) throws CoreException {

		List<ApUserOrg> list = new ArrayList<ApUserOrg>();

		ApUserOrg apUserOrg = new ApUserOrg();

		apUserOrg.setUserId(userId);

		list = getSqlMap().queryForList(NAMESPACES + "queryApUserOrgByUserId", apUserOrg);

		return list;
	}

	@Override
	public int deleteApUserOrgByUserId(String userId) throws CoreException {
		ApUserOrg apUserOrg = new ApUserOrg();
		apUserOrg.setUserId(userId);
		return getSqlMap().delete(NAMESPACES + "deleteApUserOrgByUserId", apUserOrg);
	}

	@Override
	public List<ApUserOrg> queryApUserOrgByOrgId(String orgId, int curNum, int pageNum) throws CoreException {
		ApUserOrg apUserOrg = new ApUserOrg();
		apUserOrg.setOrgId(orgId);
		return getSqlMap().queryForList(NAMESPACES + "queryApUserOrgByOrgId", apUserOrg, curNum, pageNum);
	}

	@Override
	public int queryApUserOrgCountByOrgId(String orgId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryApUserOrgCountByOrgId", orgId);
	}

	@Override
	public List<ApUser> queryUserInfoByOrgId(String orgId, int curNum, int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryUserInfoByOrgId", orgId, curNum, pageNum);
	}

	// shihuan 2017-02-25 用户管理列表查询
	@Override
	public List<ApUser> queryUserList(Map<String, Object> map, int curNum, int pageNum) {

		List<ApUser> list = new ArrayList<ApUser>();

		list = getSqlMap().queryForList(NAMESPACES + "queryUserList", map, curNum, pageNum);

		return list;
	}

	// shihuan 2017-02-25 用户管理列表查询条件
	@Override
	public int queryUserCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryUserCount", map);
	}
	//zlb
	@Override
	public ApUserOrg queryApUserOrgByUserIdMember(String userId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApUserOrgByUserId", userId);
	}
	//zlb
	@Override
	public int queryApUserOrgByOrgIdCount(String orgId) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApUserOrgByOrgIdCount", orgId);
	}

	@Override
	public String queryApUserOrgByUserCode(String userCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES+"queryApUserOrgByUserCode",userCode);
	}

	@Override
	public List<ApUserOrg> selectApUserOrgByOrgId(Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES + "selectApUserOrgByOrgId", map);
	}

	@Override
	public int selectApUserOrgCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectApUserOrgCount", map);
	}

	@Override
	public Map<String, String> selectApOrgByUserCode(String userCode) {
		return getSqlMap().queryForObject(NAMESPACES + "selectApOrgByUserCode", userCode);
	}
}
