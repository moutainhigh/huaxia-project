package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotRule;

public interface AllotRuleDao {

	int insertAllotRule(AllotRule allotRule) throws CoreException;

	int insertAllotRuleSelective(AllotRule allotRule) throws CoreException;

	int deleteAllotRuleByRuleCode(String ruleCode) throws CoreException;

	int updateAllotRule(AllotRule allotRule) throws CoreException;

	int updateAllotRuleSelective(AllotRule allotRule) throws CoreException;

	AllotRule selectAllotRuleByRuleCode(String ruleCode) throws CoreException;

	List<AllotRule> queryAllotRule() throws CoreException;

	int countAllotRuleByCondition(Map map) throws CoreException;
	
	int countRule(Map map) throws CoreException;

	List<AllotRule> queryAllotRuleByCondition(Map map, int page, int rows) throws CoreException;
	
	AllotRule queryAllotRuleByCondition(Map map);
}
