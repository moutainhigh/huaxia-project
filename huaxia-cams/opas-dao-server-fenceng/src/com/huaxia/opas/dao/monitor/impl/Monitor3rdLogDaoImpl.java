package com.huaxia.opas.dao.monitor.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.monitor.Monitor3rdLogDao;
import com.huaxia.opas.domain.monitor.Monitor3rdLog;

public class Monitor3rdLogDaoImpl extends AbstractDAO implements Monitor3rdLogDao {

	private static final long serialVersionUID = -8898997101724230462L;
	
	private static final String NAMESPACES = "Monitor3rdLogList.";

	@Override
	public List<Monitor3rdLog> queryMonitor3rdLogList(Monitor3rdLog monitor3rdLog, int curNum, int pageNum) {
		
		List<Monitor3rdLog> list = new ArrayList<Monitor3rdLog>();
		
		list = getSqlMap().queryForList(NAMESPACES + "queryMonitor3rdLogList", monitor3rdLog, curNum, pageNum);

		return list;
	}

	@Override
	public int queryMonitor3rdLogCount(Monitor3rdLog monitor3rdLog) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMonitor3rdLogCount", monitor3rdLog);
	}

}
