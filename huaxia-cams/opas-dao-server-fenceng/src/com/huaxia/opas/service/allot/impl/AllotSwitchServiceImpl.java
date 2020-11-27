package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotSwitchDao;
import com.huaxia.opas.domain.allot.AllotSwitch;
import com.huaxia.opas.service.allot.AllotSwitchService;

public class AllotSwitchServiceImpl extends AbstractDAO implements AllotSwitchService , Serializable{

	private static final long serialVersionUID = -1580300944990962888L;
	@Resource(name = "allotSwitchDao")
	private AllotSwitchDao allotSwitchDao;
	
	public AllotSwitchDao getAllotSwitchDao() {
		return allotSwitchDao;
	}

	public void setAllotSwitchDao(AllotSwitchDao allotSwitchDao) {
		this.allotSwitchDao = allotSwitchDao;
	}

	@Override
	public int countAllotSwitch(String switchCode) throws CoreException {
		return  getAllotSwitchDao().countAllotSwitch(switchCode);
	}
	
	@Override
	public int saveAllotSwitch(AllotSwitch allotSwitch) throws CoreException {
		return getAllotSwitchDao().insertAllotSwitch(allotSwitch);
	}

	@Override
	public int updateAllotSwitch(AllotSwitch allotSwitch) throws CoreException {
		return getAllotSwitchDao().updateAllotSwitch(allotSwitch);
	}

	@Override
	public AllotSwitch queryAllotSwitchByCode(String switchCode) throws CoreException {
		return getAllotSwitchDao().queryAllotSwitchByCode(switchCode);
	}

	@Override
	public int queryHoliday() throws CoreException {
		return getAllotSwitchDao().selectHoliday();
	}
}
