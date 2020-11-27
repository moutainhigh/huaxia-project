package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.Policy;
import com.huaxia.opas.dao.sysparm.PolicyDao;

/**
 * 政策码信息维护DAO层实现类
 * @author liudi
 * @since 2017-03-08
 * @version 1.0
 */
public class PolicyDaoImpl extends AbstractDAO implements PolicyDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5485541058716184496L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "Policy.";
	//添加状态为Start
	@Override
	public int savePolicyStart(Policy policy) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertPolicy1", policy);
	}
	//添加状态为End
	@Override
	public int savePolicyEnd(Policy policy) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertPolicy2", policy);
	}
	//修改
	@Override
	public int updatePolicy(Policy policy) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updatePolicy", policy);
	}
	//删除
	@Override
	public int deletePolicy(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deletePolicyById", autoId);
	}
	//件数查询
	@Override
	public int queryPolicyCount(Policy policy) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryPolicyCount", policy);
	}
	//查询
	@Override
	public List<Policy> queryPolicy(Policy policy,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryPolicy1", policy, curNum, pageNum);
	}
	//点击查询后，触发查询和排序功能
	@Override
	public List<Policy> queryPolicy(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryPolicy2", params, curNum, pageNum);
	}
	//查询是否重复
	@Override
	public Policy queryPolicy(Policy policy) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryPolicy3", policy);
	}
	@Override
	public List<Policy> queryPolicyCondition(Map<String, String> map) {
		return getSqlMap().queryForList(NAMESPACES + "queryPolicyCondition", map);
	}
	@Override
	public String queryPolicyStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPolicyStatus", autoId);
	}
	@Override
	public int updatePolicyWithoutStatus(Policy policy) {
		return getSqlMap().update(NAMESPACES + "updatePolicyWithoutStatus", policy);
	}
}
