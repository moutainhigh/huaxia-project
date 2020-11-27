package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotNumber;
import com.huaxia.opas.domain.allot.AllotRule;

public interface AllotRuleService {

	 int saveAllotRule(AllotRule allotRule) throws CoreException;

	 int saveAllotRuleSelective(AllotRule allotRule) throws CoreException;

	 int removeAllotRuleByRuleCode(String ruleCode) throws CoreException;

	 int updateAllotRule(AllotRule allotRule) throws CoreException;

	 int updateAllotRuleSelective(AllotRule allotRule) throws CoreException;

	 AllotRule queryAllotRuleByRuleCode(String ruleCode) throws CoreException;

	 List<AllotRule> queryAllotRule() throws CoreException;

	 int countAllotRuleByCondition(Map map) throws CoreException;
	
	 int countRule(Map map) throws CoreException;

	 List<AllotRule> queryAllotRuleByCondition(Map map, int page, int rows) throws CoreException;
	 
	 int saveAllotNumber(AllotNumber allotNumber) throws CoreException;
	 
	 int updateAllotNumber(AllotNumber allotNumber) throws CoreException;

	 AllotNumber queryAllotNumber(Map map) throws CoreException;
	 
	 List<AllotNumber> queryAllotNumberList(Map map) throws CoreException;
	 
	 String countAllotNumber(Map map) throws CoreException;
	 
	 AllotRule queryAllotRuleByCondition(Map map) throws CoreException;
	 /**
	  * 根据条件查询征信规则分配数量方法-wenyh
	  * @param map
	  * @return
	  * @throws CoreException
	  */
	 String queryCreditRuleAllotNumber(Map map) throws CoreException;
}
