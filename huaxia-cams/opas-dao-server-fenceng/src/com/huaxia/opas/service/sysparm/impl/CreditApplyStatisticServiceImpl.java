package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.CreditApplyStatisticDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.CreditApplyStatisticService;

public class CreditApplyStatisticServiceImpl extends AbstractService implements CreditApplyStatisticService,Serializable  {
	
	private static final long serialVersionUID = 1678672601627872966L;

	@Resource(name="creditApplyStatisticDao")
	private CreditApplyStatisticDao creditApplyStatisticDao;

	@Override
	public int queryCreditApplyCount(Map<String, Object> map) throws CoreException{
		return creditApplyStatisticDao.queryCreditApplyCount(map);
	}

	@Override
	public int querySeniorApplyCount(Map<String, Object> map) throws CoreException{
		return creditApplyStatisticDao.querySeniorApplyCount(map);
	}

	@Override
	public int queryFinishedCount(Map<String, Object> map) throws CoreException{
		return creditApplyStatisticDao.queryFinishedCount(map);
	}
	

}
