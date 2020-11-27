package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotTime;
//规则与 队列  组  映射
public interface AllotMappingRuleService {
	// 插入映射表
	int saveMappingRule(AllotMappingRule mappingRule) throws CoreException;

	// 更改映射关系
	 int updateMappingRule(AllotMappingRule mappingRule) throws CoreException;

	// 删除映射关系
	 int removeMappingRuleById(String mappingId) throws CoreException;

	 int removeMappingRuleByRuleCode(String ruleCode) throws CoreException;
	// 遍历映射关系
	int countMappingRule(Map map) throws CoreException;
	
	 List<AllotMappingRule> queryMappingRule(Map map, int page, int rows) throws CoreException;
	 
	 int queryNum(AllotMappingRule mappingRule) throws CoreException;
	//查询规则映射是否重复
	 AllotMappingRule queryRule(Map map) throws CoreException;
	// 查询全部
	 List<AllotMappingRule> queryAllMappingRule(Map map) throws CoreException;

	// 规则扫描
	List<AllotMappingRule> queryRule(String ruleType) throws CoreException;
	
	//
	 List<String> queryRuleCode(String ruleType) throws CoreException;
	 
	 List<String> selectRuleCodeByMap(Map map) throws CoreException;
	 
	 int saveAllotTime(AllotTime allotTime) throws CoreException;
	 
	 int updateAllotTime(AllotTime allotTime) throws CoreException;
	 
	 int removeAllotTime(String id) throws CoreException;
	 
	 int countAllotTime(Map map) throws CoreException;
	 
	 List<AllotTime> queryAllotTime(Map map, int page, int rows) throws CoreException;
	 
	 AllotTime queryAllotTime(Map map) throws CoreException;
	 
}
