package com.huaxia.opas.service.monitor.impl;

import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.monitor.Monitor3rdLogDao;
import com.huaxia.opas.domain.monitor.Monitor3rdLog;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.monitor.Monitor3rdLogService;

public class Monitor3rdLogServiceImpl extends AbstractService implements Monitor3rdLogService {

	private static final long serialVersionUID = 5714122290560896273L;
	
	@Resource(name = "monitor3rdLogDao")
	private Monitor3rdLogDao monitor3rdLogDao;
	
	public Monitor3rdLogDao getMonitor3rdLogDao() {
		return monitor3rdLogDao;
	}

	public void setMonitor3rdLogDao(Monitor3rdLogDao monitor3rdLogDao) {
		this.monitor3rdLogDao = monitor3rdLogDao;
	}

	@Override
	public int queryMonitor3rdLogCount(Monitor3rdLog monitor3rdLog) {
		return getMonitor3rdLogDao().queryMonitor3rdLogCount(monitor3rdLog);
	}

	@Override
	public List<Monitor3rdLog> queryMonitor3rdLogList(Monitor3rdLog monitor3rdLog, int curNum, int pageNum) {
		return getMonitor3rdLogDao().queryMonitor3rdLogList(monitor3rdLog, curNum, pageNum);
	}

}
