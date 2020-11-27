package com.huaxia.opas.service.monitor.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.monitor.Monitor3rdDao;
import com.huaxia.opas.domain.monitor.Monitor3rdList;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.monitor.Monitor3rdService;

public class Monitor3rdServiceImpl extends AbstractService implements Monitor3rdService, Serializable {

	private static final long serialVersionUID = 1863395236189984799L;
	
	@Resource(name = "monitor3rdDao")
	private Monitor3rdDao monitor3rdDao;
	
	public Monitor3rdDao getMonitor3rdDao() {
		return monitor3rdDao;
	}

	public void setMonitor3rdDao(Monitor3rdDao monitor3rdDao) {
		this.monitor3rdDao = monitor3rdDao;
	}
	

	@Override
	public List<Monitor3rdList> queryMonitor3rdList(Monitor3rdList monitor3rdList, int curNum, int pageNum) {
		return getMonitor3rdDao().queryMonitor3rdList(monitor3rdList, curNum, pageNum);
	}

	@Override
	public int queryMonitor3rdCount(Monitor3rdList monitor3rdList) {
		return getMonitor3rdDao().queryMonitor3rdCount(monitor3rdList);
	}

	@Override
	public Monitor3rdList selectById(String id) throws CoreException {
		return getMonitor3rdDao().selectById(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Monitor3rdList monitor3rdList) throws CoreException {
		return getMonitor3rdDao().updateByPrimaryKeySelective(monitor3rdList);
	}

	@Override
	public int updateStatusSelective(Monitor3rdList monitor3rdList) throws CoreException {
		return getMonitor3rdDao().updateStatusSelective(monitor3rdList);
	}


}
