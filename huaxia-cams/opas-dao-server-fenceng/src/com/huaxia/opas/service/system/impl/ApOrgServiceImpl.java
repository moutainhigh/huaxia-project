package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApOrgDao;
import com.huaxia.opas.domain.system.ApOrg;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApOrgService;

public class ApOrgServiceImpl extends AbstractService implements ApOrgService , Serializable{

	private static final long serialVersionUID = -4286276550554328492L;

	@Resource(name = "apOrgDao")
	private ApOrgDao apOrgDao;

	@Override
	public int insertApOrg(ApOrg apOrg) throws CoreException {
		return getApOrgDao().insertApOrg(apOrg);
	}

	@Override
	public int insertApOrgSelective(ApOrg apOrg) throws CoreException {
		return getApOrgDao().insertApOrgSelective(apOrg);
	}

	@Override
	public int deleteApOrgByOrgId(String orgId) throws CoreException {
		return getApOrgDao().deleteApOrgByOrgId(orgId);
	}

	@Override
	public int updateApOrg(ApOrg apOrg) throws CoreException {
		return getApOrgDao().updateApOrg(apOrg);
	}

	@Override
	public int updateApOrgSelective(ApOrg apOrg) throws CoreException {
		return getApOrgDao().updateApOrgSelective(apOrg);
	}

	@Override
	public ApOrg queryApOrgByOrgId(String orgId) throws CoreException {
		return getApOrgDao().queryApOrgByOrgId(orgId);
	}

	@Override
	public List<ApOrg> queryOrgList(ApOrg apOrg, int curNum, int pageNum)
			throws CoreException {
		return getApOrgDao().queryOrgList(apOrg, curNum, pageNum);
	}

	@Override
	public List<ApOrg> queryOrgListWithoutPage(ApOrg apOrg)
			throws CoreException {
		return getApOrgDao().queryOrgListWithoutPage(apOrg);
	}

	@Override
	public int queryOrgCount(ApOrg apOrg) throws CoreException {
		return getApOrgDao().queryOrgCount(apOrg);
	}

	@Override
	public List<ApOrg> queryUserOrgs(String userId) {
		return getApOrgDao().queryUserOrgs(userId);
	}

	@Override
	public int queryOrgCountByOrgNo(String orgNo) throws CoreException {
		return getApOrgDao().queryOrgCountByOrgNo(orgNo);
	}

	@Override
	public List<ApOrg> queryOrgByLevel() throws CoreException {
		return getApOrgDao().queryOrgByLevel();
	}
	
	//用户筛选条件  组别  shihuan  2017-04-14
	@Override
	public List<ApOrg> queryOrgForUser()throws CoreException {
		return getApOrgDao().queryOrgForUser();
	}
	
	//用户筛选条件  组别  shihuan  2017-04-14
	@Override
	public int queryOrgName(String orgName)throws CoreException {
		return getApOrgDao().queryOrgName(orgName);
	}
	
	public ApOrgDao getApOrgDao() {
		return apOrgDao;
	}

	public void setApOrgDao(ApOrgDao apOrgDao) {
		this.apOrgDao = apOrgDao;
	}
	
	//wangdebin 排查重复的orgNo被更新上
	@Override
	public ApOrg queryOrgByOrgNo(String orgNo) throws CoreException {
		return getApOrgDao().queryOrgByOrgNo(orgNo);
	}
	
	@Override
	public List<Map<String, Object>> queryOrgByOrgLevel(Map<String, Object> map) throws CoreException {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> orgMap = new HashMap<String, Object>();
		orgMap.put("orgNo", "");
		orgMap.put("orgName", "--请选择--");
		listMap.add(orgMap);
		List<Map<String, Object>> resultList = getApOrgDao().queryOrgByOrgLevel(map);
		if(!resultList.isEmpty()){
			listMap.addAll(resultList);
		}
		return listMap;
	}
}
