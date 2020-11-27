package com.huaxia.opas.service.system;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;

public interface ApUserOrgService {

	public int insertApUserOrg(ApUserOrg apUserOrg) throws CoreException;

	public int insertApUserOrgSelective(ApUserOrg apUserOrg)
			throws CoreException;

	public int deleteApUserOrgByOrgIdAndUserId(String orgId, String userId)
			throws CoreException;

	public int updateApUserOrg(ApUserOrg apUserOrg) throws CoreException;

	public int updateApUserOrgSelective(ApUserOrg apUserOrg)
			throws CoreException;

	public ApUserOrg queryApUserOrgByOrgIdAndUserId(String orgId, String userId)
			throws CoreException;

	public List<ApUserOrg> queryApUserOrgByUserId(String userId)
			throws CoreException;

	public List<ApUserOrg> queryApUserOrgByOrgId(String orgId, int curNum,
			int pageNum) throws CoreException;

	public int deleteApUserOrgByUserId(String userId) throws CoreException;

	public int queryApUserOrgCountByOrgId(String orgId);

	public List<ApUser> queryUserInfoByOrgId(String orgId, int curNum,
			int pageNum) throws CoreException;

	// shihuan 2017-02-25 用户管理列表查询
	public List<ApUser> queryUserList(Map<String, Object> map, int curNum,
			int pageNum);

	// shihuan 2017-02-25 用户管理列表查询 条件
	public int queryUserCount(Map<String, Object> map);
	/********************************
	 *@describe:根据userCode查询用户所在组
	 *@author：susha
	 *@date:2017-04-17
	 *********************************/
	public String queryApUserOrgByUserCode(String userCode) throws CoreException;

}
