package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotMappingRule;
//规则与 队列  组  映射
public interface AllotMappingRuleDao {
	// 插入映射表
	int insertMappingRule(AllotMappingRule mappingRule) throws CoreException;

	// 更改映射关系
	 int updateMappingRule(AllotMappingRule mappingRule) throws CoreException;

	// 删除映射关系
	 int deleteMappingRuleById(String mappingId) throws CoreException;

	 int deleteMappingRuleByRuleCode(String ruleCode) throws CoreException;
	// 遍历映射关系
    int countMappingRule(Map map) throws CoreException;
	
	List<AllotMappingRule> selectMappingRule(Map map, int page, int rows) throws CoreException;
	
	 int selectNum(AllotMappingRule mappingRule) throws CoreException;
	
	// 查询全部
	 List<AllotMappingRule> selectMappingRule(Map map) throws CoreException;

	// 规则扫描
	List<AllotMappingRule> queryRule(String ruleType) throws CoreException;
	
	AllotMappingRule selectRule(Map map) throws CoreException;
	
	//
	List<String> selectRuleCode(String ruleType) throws CoreException;
	
	List<String> selectRuleCodeByMap(Map map) throws CoreException;
}
