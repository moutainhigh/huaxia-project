package com.huaxia.opas.dao.sysparm.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.CreditApplyStatisticDao;

public class CreditApplyStatisticDaoImpl extends AbstractDAO implements CreditApplyStatisticDao {

	private static final long serialVersionUID = 495518709212054335L;
	
	private static final String NAMESPACES = "CreditApplyStatistic.";

	@Override
	public int queryCreditApplyCount(Map<String, Object> map) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditApplyCount", map);
	}

	@Override
	public int querySeniorApplyCount(Map<String, Object> map) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "querySeniorApplyCount", map);
	}

	@Override
	public int queryFinishedCount(Map<String, Object> map) throws CoreException{
		return getSqlMap().queryForObject(NAMESPACES + "queryFinishedCount", map);
	}
	

}
