package com.huaxia.opas.dao.allot.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotRuleDao;
import com.huaxia.opas.domain.allot.AllotRule;

public class AllotRuleDaoImpl extends AbstractDAO implements AllotRuleDao {

	private static final long serialVersionUID = -6239319727725807567L;
	private static final String NAMESPACES = "AllotRule.";

	@Override
	public int insertAllotRule(AllotRule allotRule) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAllotRule", allotRule);
	}

	@Override
	public int insertAllotRuleSelective(AllotRule allotRule) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAllotRuleSelective", allotRule);
	}

	@Override
	public int deleteAllotRuleByRuleCode(String ruleCode) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteAllotRuleByRuleCode", ruleCode);
	}

	@Override
	public int updateAllotRule(AllotRule allotRule) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAllotRule", allotRule);
	}

	@Override
	public int updateAllotRuleSelective(AllotRule allotRule) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAllotRuleSelective", allotRule);
	}

	@Override
	public AllotRule selectAllotRuleByRuleCode(String ruleCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectAllotRuleByRuleCode", ruleCode);
	}

	@Override
	public List<AllotRule> queryAllotRule() throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryAllotRule");
	}

	@Override
	public int countAllotRuleByCondition(Map map) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES + "countAllotRuleByCondition",map);
	}
	
	@Override
	public int countRule(Map map) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES + "countRule",map);
	}

	@Override
	public List<AllotRule> queryAllotRuleByCondition(Map map, int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryAllotRuleByCondition",map,currentPage, pageSize);
	}

	@Override
	public AllotRule queryAllotRuleByCondition(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAllotRuleByCondition",map);
	}
}
