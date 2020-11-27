package com.huaxia.opas.dao.system;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApOrg;

public interface ApOrgDao {

	public int insertApOrg(ApOrg apOrg) throws CoreException;

	public int insertApOrgSelective(ApOrg apOrg) throws CoreException;

	public int deleteApOrgByOrgId(String orgId) throws CoreException;

	public int updateApOrg(ApOrg apOrg) throws CoreException;

	public int updateApOrgSelective(ApOrg apOrg) throws CoreException;

	public ApOrg queryApOrgByOrgId(String orgId) throws CoreException;

	public List<ApOrg> queryOrgList(ApOrg apOrg, int curNum, int pageNum) throws CoreException;

	public List<ApOrg> queryOrgListWithoutPage(ApOrg apOrg) throws CoreException;

	public int queryOrgCount(ApOrg apOrg) throws CoreException;

	public List<ApOrg> queryUserOrgs(String userId);

	public int queryOrgCountByOrgNo(String orgNo) throws CoreException;

	public List<ApOrg> queryOrgByLevel() throws CoreException;
	
	//用户筛选条件  组别  shihuan  2017-04-14
	public List<ApOrg> queryOrgForUser() throws CoreException;
	
	//添加时校验  机构名称不能相同  shihuan  2017-04-24 
	public int queryOrgName(String orgName) throws CoreException;
	
	//查询组名  组ID  zlb
	List<Map<String,String>> queryApOrg() throws CoreException;
	
	//排查重复orgNo被更新上
	public ApOrg queryOrgByOrgNo(String orgNo) throws CoreException;
	/**
	 * 根据组级别查询组数据
	 * @Author wenyh-2020-10-20
	 * @return
	 * @throws CoreException
	 */
	public List<Map<String, Object>> queryOrgByOrgLevel(Map<String, Object> map) throws CoreException;
}
