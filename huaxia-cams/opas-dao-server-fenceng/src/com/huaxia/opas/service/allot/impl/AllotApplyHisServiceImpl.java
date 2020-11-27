package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.service.allot.AllotApplyHisService;

public class AllotApplyHisServiceImpl extends AbstractDAO implements AllotApplyHisService, Serializable  {

	private static final long serialVersionUID = -1760354598668940119L;
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyHisDao;
	
	
	public AllotApplyAllotHisDao getAllotApplyHisDao() {
		return allotApplyHisDao;
	}

	public void setAllotApplyHisDao(AllotApplyAllotHisDao allotApplyHisDao) {
		this.allotApplyHisDao = allotApplyHisDao;
	}

	@Override
	public int saveAllotApplyHis(AllotApplyAllotHis allotApplyHis) throws CoreException {
		return getAllotApplyHisDao().insertSelective(allotApplyHis);
	}

	@Override
	public int saveAllotApplyHis(Map map) throws CoreException {
		return getAllotApplyHisDao().insertCopyOpasAllotToAllotHis(map);
	}
	
	@Override
	public int saveHisBatch(List<String> lifeList) throws CoreException {
		return getAllotApplyHisDao().insertHisBatch(lifeList);
	}
}
