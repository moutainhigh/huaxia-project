package com.huaxia.opas.service.sysparm;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Policy;
/**
 * 政策码信息维护服务层接口
 * @author liudi
 * @since 2017-03-08
 * @version 1.0
 */
public interface PolicyService {
	//添加状态为Start
	public int savePolicyStart(Policy policy) throws CoreException;
	//添加状态为End
	public int savePolicyEnd(Policy policy) throws CoreException;
	//修改
	public int updatePolicy(Policy policy) throws CoreException;
	//删除
	public int deletePolicy(String autoId) throws CoreException;
	//件数查询
	public int queryPolicyCount(Policy policy) throws CoreException;
	//查询
	public List<Policy> queryPolicy(Policy policy,int curNum,int pageNum) throws CoreException;
	//点击查询后，触发查询和排序功能
	public List<Policy> queryPolicy(Map<String,Object> params,int curNum,int pageNum) throws CoreException;
	//查询是否重复
	public Policy queryPolicy(Policy policy) throws CoreException;
	
	List<Policy> queryPolicyCondition(Map<String, String> map);
	//查询修改前的政策码的状态
	public String queryPolicyStatus(String autoId);
	//修改政策码信息(不包括状态)
	public int updatePolicyWithoutStatus(Policy policy);

}
