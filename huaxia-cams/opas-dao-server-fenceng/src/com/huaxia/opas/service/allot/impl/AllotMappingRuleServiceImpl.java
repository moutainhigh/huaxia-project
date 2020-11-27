package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotMappingRuleDao;
import com.huaxia.opas.dao.allot.AllotTimeDao;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotTime;
import com.huaxia.opas.service.allot.AllotMappingRuleService;

public class AllotMappingRuleServiceImpl extends AbstractDAO implements AllotMappingRuleService, Serializable {

	private static final long serialVersionUID = -4653090968402281051L;
	@Resource(name = "allotMappingRuleDao")
	private AllotMappingRuleDao allotMappingRuleDao;
	
	@Resource(name = "allotTimeDao")
	private AllotTimeDao allotTimeDao;
	
	public AllotMappingRuleDao getAllotMappingRuleDao() {
		return allotMappingRuleDao;
	}

	public void setAllotMappingRuleDao(AllotMappingRuleDao allotMappingRuleDao) {
		this.allotMappingRuleDao = allotMappingRuleDao;
	}

	@Override
	public int saveMappingRule(AllotMappingRule mappingRule) throws CoreException {
		return getAllotMappingRuleDao().insertMappingRule(mappingRule);
	}

	@Override
	public int updateMappingRule(AllotMappingRule mappingRule) throws CoreException {
		return getAllotMappingRuleDao().updateMappingRule(mappingRule);
	}

	@Override
	public int removeMappingRuleById(String mappingId) throws CoreException {
		return getAllotMappingRuleDao().deleteMappingRuleById(mappingId);
	}
	
	@Override
	public int removeMappingRuleByRuleCode(String ruleCode) throws CoreException {
		return getAllotMappingRuleDao().deleteMappingRuleByRuleCode(ruleCode);
	} 
	//查询   全部 映射 信息
	
	@Override
	public int countMappingRule(Map map) throws CoreException {
		return getAllotMappingRuleDao().countMappingRule(map);
	}

	@Override
	public List<AllotMappingRule> queryMappingRule(Map map,int currentPage, int pageSize) throws CoreException {
		return getAllotMappingRuleDao().selectMappingRule(map, currentPage, pageSize);
	}
	
	@Override
	public int queryNum(AllotMappingRule mappingRule) throws CoreException {
		return getAllotMappingRuleDao().selectNum(mappingRule);
	}
	
	@Override
	public List<AllotMappingRule> queryAllMappingRule(Map map) throws CoreException {
		return getAllotMappingRuleDao().selectMappingRule(map);
	}
	//规则
	@Override
	public List<AllotMappingRule> queryRule(String ruleType) throws CoreException {
		return getAllotMappingRuleDao().queryRule(ruleType);
	}	
	
	@Override
	public AllotMappingRule queryRule(Map map) throws CoreException {
		return getAllotMappingRuleDao().selectRule(map);
	}
	
	@Override
	public List<String> queryRuleCode(String ruleType) throws CoreException {
		return getAllotMappingRuleDao().selectRuleCode(ruleType);
	}
	
	@Override
	public List<String> selectRuleCodeByMap(Map map) throws CoreException {
		return getAllotMappingRuleDao().selectRuleCodeByMap(map);
	}
	
	@Override
	public int saveAllotTime(AllotTime allotTime) throws CoreException{
		return allotTimeDao.insertAllotTime(allotTime);
	}
	
	@Override
	public int updateAllotTime(AllotTime allotTime) throws CoreException {
		return allotTimeDao.updateAllotTime(allotTime);
	}
	@Override
	public int removeAllotTime(String id) throws CoreException {
		return allotTimeDao.deleteAllotTime(id);
	}
	
	@Override
	public int countAllotTime(Map map) throws CoreException {
		return allotTimeDao.countAllotTime(map);
	}
	
	@Override
	public List<AllotTime> queryAllotTime(Map map, int page, int rows) throws CoreException {
		return allotTimeDao.selectAllotTime(map, page, rows);
	}
	
	@Override
	public AllotTime queryAllotTime(Map map) throws CoreException {
		return allotTimeDao.selectAllotTime(map);
	}
}
