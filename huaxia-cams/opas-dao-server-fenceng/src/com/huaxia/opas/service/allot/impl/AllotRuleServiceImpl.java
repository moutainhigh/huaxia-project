package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotMappingRuleDao;
import com.huaxia.opas.dao.allot.AllotNumberDao;
import com.huaxia.opas.dao.allot.AllotRuleDao;
import com.huaxia.opas.domain.allot.AllotNumber;
import com.huaxia.opas.domain.allot.AllotRule;
import com.huaxia.opas.service.allot.AllotRuleService;

public class AllotRuleServiceImpl extends AbstractDAO implements AllotRuleService , Serializable {
	
	private static final long serialVersionUID = 3397267075649566786L;
	@Resource(name = "allotRuleDao")
	private AllotRuleDao allotRuleDao;
	
	@Resource(name = "allotMappingRuleDao")
	private AllotMappingRuleDao allotMappingRuleDao;
	
	@Resource(name = "allotNumberDao")
	private AllotNumberDao allotNumberDao;
	
	public AllotRuleDao getAllotRuleDao() {
		return allotRuleDao;
	}

	public void setAllotRuleDao(AllotRuleDao allotRuleDao) {
		this.allotRuleDao = allotRuleDao;
	}

	@Override
	public int saveAllotRule(AllotRule allotRule) throws CoreException {
		return getAllotRuleDao().insertAllotRule(allotRule);
	}

	@Override
	public int saveAllotRuleSelective(AllotRule allotRule) throws CoreException {
		return getAllotRuleDao().insertAllotRuleSelective(allotRule);
	}

	@Override
	public int removeAllotRuleByRuleCode(String ruleCode) throws CoreException {
		//先删除映射表该规则映射情况
		allotMappingRuleDao.deleteMappingRuleByRuleCode(ruleCode);
		return getAllotRuleDao().deleteAllotRuleByRuleCode(ruleCode);
	}

	@Override
	public int updateAllotRule(AllotRule allotRule) throws CoreException {
		return getAllotRuleDao().updateAllotRule(allotRule);
	}

	@Override
	public int updateAllotRuleSelective(AllotRule allotRule) throws CoreException {
		return getAllotRuleDao().updateAllotRuleSelective(allotRule);
	}

	@Override
	public AllotRule queryAllotRuleByRuleCode(String ruleCode) throws CoreException {
		return getAllotRuleDao().selectAllotRuleByRuleCode(ruleCode);
	}

	@Override
	public List<AllotRule> queryAllotRule() throws CoreException {
		return getAllotRuleDao().queryAllotRule();
	}

	@Override
	public int countAllotRuleByCondition(Map map) throws CoreException {
		 return getAllotRuleDao().countAllotRuleByCondition(map);
	}

	@Override
	public int countRule(Map map) throws CoreException {
		 return getAllotRuleDao().countRule(map);
	}
	
	@Override
	public List<AllotRule> queryAllotRuleByCondition(Map map, int currentPage, int pageSize) throws CoreException {
		return getAllotRuleDao().queryAllotRuleByCondition(map, currentPage, pageSize);
	}

	@Override
	public int saveAllotNumber(AllotNumber allotNumber) throws CoreException {
		return allotNumberDao.insertAllotNumber(allotNumber);
	}
	
	@Override
	public int updateAllotNumber(AllotNumber allotNumber) throws CoreException {
		return allotNumberDao.updateAllotNumber(allotNumber);
	}
	
	@Override
	public AllotNumber queryAllotNumber(Map map) throws CoreException {
		return allotNumberDao.selectAllotNumber(map);
	}
	
	@Override
	public List<AllotNumber> queryAllotNumberList(Map map) throws CoreException {
		return allotNumberDao.selectAllotNumberList(map);
	}
	
	@Override
	public String countAllotNumber(Map map) throws CoreException {
		 return allotNumberDao.countAllotNumber(map);
	}
	
	@Override
	public AllotRule queryAllotRuleByCondition(Map map) throws CoreException {
		return getAllotRuleDao().queryAllotRuleByCondition(map);
	}
	
	@Override
	public String queryCreditRuleAllotNumber(Map map) throws CoreException {
		return allotNumberDao.queryCreditRuleAllotNumber(map);
	}
	
}
