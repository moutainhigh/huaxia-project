package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApUserOrgDao;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApUserOrgService;

public class ApUserOrgServiceImpl extends AbstractService implements
		ApUserOrgService, Serializable {

	private static final long serialVersionUID = 4723745660856876227L;

	@Resource(name = "apUserOrgDao")
	private ApUserOrgDao apUserOrgDao;

	@Override
	public int insertApUserOrg(ApUserOrg apUserOrg) throws CoreException {
		return getApUserOrgDao().insertApUserOrg(apUserOrg);
	}

	@Override
	public int insertApUserOrgSelective(ApUserOrg apUserOrg)
			throws CoreException {
		return getApUserOrgDao().insertApUserOrgSelective(apUserOrg);
	}

	@Override
	public int deleteApUserOrgByOrgIdAndUserId(String orgId, String userId)
			throws CoreException {
		return getApUserOrgDao().deleteApUserOrgByUserId(userId);
	}

	@Override
	public int updateApUserOrg(ApUserOrg apUserOrg) throws CoreException {
		return getApUserOrgDao().updateApUserOrg(apUserOrg);
	}

	@Override
	public int updateApUserOrgSelective(ApUserOrg apUserOrg)
			throws CoreException {
		return getApUserOrgDao().updateApUserOrgSelective(apUserOrg);
	}

	@Override
	public ApUserOrg queryApUserOrgByOrgIdAndUserId(String orgId, String userId)
			throws CoreException {
		return getApUserOrgDao().queryApUserOrgByOrgIdAndUserId(orgId, userId);
	}

	@Override
	public List<ApUserOrg> queryApUserOrgByUserId(String userId)
			throws CoreException {
		return getApUserOrgDao().queryApUserOrgByUserId(userId);
	}

	@Override
	public List<ApUserOrg> queryApUserOrgByOrgId(String orgId, int curNum,
			int pageNum) throws CoreException {
		return getApUserOrgDao().queryApUserOrgByOrgId(orgId, curNum, pageNum);
	}

	@Override
	public int deleteApUserOrgByUserId(String userId) throws CoreException {
		return getApUserOrgDao().deleteApUserOrgByUserId(userId);
	}

	@Override
	public int queryApUserOrgCountByOrgId(String orgId) {
		return getApUserOrgDao().queryApUserOrgCountByOrgId(orgId);
	}

	@Override
	public List<ApUser> queryUserInfoByOrgId(String orgId, int curNum,
			int pageNum) throws CoreException {
		return getApUserOrgDao().queryUserInfoByOrgId(orgId, curNum, pageNum);
	}

	@Override
	public List<ApUser> queryUserList(Map<String, Object> map, int curNum,
			int pageNum) {
		return getApUserOrgDao().queryUserList(map, curNum, pageNum);
	}

	@Override
	public int queryUserCount(Map<String, Object> map) {
		return getApUserOrgDao().queryUserCount(map);
	}

	@Override
	public String queryApUserOrgByUserCode(String userCode) throws CoreException {
		return getApUserOrgDao().queryApUserOrgByUserCode(userCode);
	}

	public ApUserOrgDao getApUserOrgDao() {
		return apUserOrgDao;
	}

	public void setApUserOrgDao(ApUserOrgDao apUserOrgDao) {
		this.apUserOrgDao = apUserOrgDao;
	}


}
