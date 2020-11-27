package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.Policy;
import com.huaxia.opas.dao.sysparm.PolicyDao;
import com.huaxia.opas.service.sysparm.PolicyService;
/**
 * 政策码信息维护服务层实现类
 * @author liudi
 * @since 2017-03-08
 * @version 1.0
 */
public class PolicyServiceImpl extends AbstractDAO implements PolicyService,Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8695491138818599586L;

	@Resource(name = "policyDao")
	private PolicyDao policyDao;

	public PolicyDao getPolicyDao() {
		return policyDao;
	}

	public void setPolicyDao(PolicyDao policyDao) {
		this.policyDao = policyDao;
	}

	//添加状态为Start
	public int savePolicyStart(Policy policy) throws CoreException {
		return getPolicyDao().savePolicyStart(policy);
	}
	
	//添加状态为End
	public int savePolicyEnd(Policy policy) throws CoreException {
		return getPolicyDao().savePolicyEnd(policy);
	}
	
	//修改
	public int updatePolicy(Policy policy) throws CoreException {
		return getPolicyDao().updatePolicy(policy);
	}
	
	//删除
	public int deletePolicy(String autoId) throws CoreException {
		return getPolicyDao().deletePolicy(autoId);
	}
	
	//件数查询
	public int queryPolicyCount(Policy policy) throws CoreException {
		return getPolicyDao().queryPolicyCount(policy);
	}
	
	//查询
	public List<Policy> queryPolicy(Policy policy,int curNum,int pageNum) throws CoreException {
		return getPolicyDao().queryPolicy(policy, curNum, pageNum);
	}
	
	//点击查询后，触发查询和排序功能
	public List<Policy> queryPolicy(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getPolicyDao().queryPolicy(params, curNum, pageNum);
	}
	
	//查询是否重复
	public Policy queryPolicy(Policy policy) throws CoreException {
		return getPolicyDao().queryPolicy(policy);
	}
	
	@Override
	public List<Policy> queryPolicyCondition(Map<String, String> map) {
		return getPolicyDao().queryPolicyCondition(map);
	}

	@Override
	public String queryPolicyStatus(String autoId) {
		return getPolicyDao().queryPolicyStatus(autoId);
	}

	@Override
	public int updatePolicyWithoutStatus(Policy policy) {
		return getPolicyDao().updatePolicyWithoutStatus(policy);
	}

}
