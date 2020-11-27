package com.huaxia.opas.dao.system;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.domain.system.ApUserOrg;

public interface ApUserOrgDao {

	public int insertApUserOrg(ApUserOrg apUserOrg) throws CoreException;

	public int insertApUserOrgSelective(ApUserOrg apUserOrg) throws CoreException;

	public int deleteApUserOrgByOrgIdAndUserId(String orgId, String userId) throws CoreException;

	public int updateApUserOrg(ApUserOrg apUserOrg) throws CoreException;

	public int updateApUserOrgSelective(ApUserOrg apUserOrg) throws CoreException;

	public ApUserOrg queryApUserOrgByOrgIdAndUserId(String orgId, String userId) throws CoreException;

	public List<ApUserOrg> queryApUserOrgByUserId(String userId) throws CoreException;

	public List<ApUserOrg> queryApUserOrgByOrgId(String orgId, int curNum, int pageNum) throws CoreException;

	public int deleteApUserOrgByUserId(String userId) throws CoreException;

	public int queryApUserOrgCountByOrgId(String orgId);

	public List<ApUser> queryUserInfoByOrgId(String orgId, int curNum, int pageNum) throws CoreException;

	public List<ApUser> queryUserList(Map<String, Object> map, int curNum, int pageNum);

	public int queryUserCount(Map<String, Object> map);
	
	//zlb
	public ApUserOrg queryApUserOrgByUserIdMember(String userId) throws CoreException;
	//zlb
	public int queryApUserOrgByOrgIdCount(String userId) throws CoreException;
	/********************************
	 *@describe:根据userCode查询组别
	 *@author：susha
	 *@date:2017-04-17
	 *********************************/
	public String queryApUserOrgByUserCode(String userCode) throws CoreException;

	public List<ApUserOrg> selectApUserOrgByOrgId(Map<String, Object> map);

	public int selectApUserOrgCount(Map<String, Object> map);

	public Map<String, String> selectApOrgByUserCode(String userCode);

}
