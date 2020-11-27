package com.huaxia.opas.dao.allot.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotMappingRuleDao;
import com.huaxia.opas.domain.allot.AllotMappingRule;

public class AllotMappingRuleDaoImpl extends AbstractDAO implements AllotMappingRuleDao {

	private static final long serialVersionUID = 8783903925406490131L;
	private static final String NAMESPACES = "MappingRule.";

	@Override
	public int insertMappingRule(AllotMappingRule mappingRule) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertMappingRule", mappingRule);
	}

	@Override
	public int updateMappingRule(AllotMappingRule mappingRule) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateMappingRule", mappingRule);
	}

	@Override
	public int deleteMappingRuleById(String mappingId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteMappingRuleById", mappingId);
	}
	
	@Override
	public int deleteMappingRuleByRuleCode(String ruleCode) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteMappingRuleByRuleCode", ruleCode);
	}
	//查询   全部 映射 信息
	
	@Override
	public int countMappingRule(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "countMappingRule",map);
	}

	@Override
	public List<AllotMappingRule> selectMappingRule(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectMappingRule",map,currentPage , pageSize);
	}
	
	@Override
	public int selectNum(AllotMappingRule mappingRule) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectNum",mappingRule);
	}
	
	@Override
	public List<AllotMappingRule> selectMappingRule(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectMappingRule",map);
	}
	//规则
	@Override
	public List<AllotMappingRule> queryRule(String ruleType) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryRule",ruleType);
	}
	
	@Override
	public AllotMappingRule selectRule(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectMappingRule",map);
	}
	
	@Override
	public List<String> selectRuleCode(String ruleType) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectRuleCode",ruleType);
	}
	
	@Override
	public List<String> selectRuleCodeByMap(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "selectRuleCodeByMap",map);
	}
}
