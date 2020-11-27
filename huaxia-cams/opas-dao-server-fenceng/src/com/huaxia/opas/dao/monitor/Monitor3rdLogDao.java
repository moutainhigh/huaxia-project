package com.huaxia.opas.dao.monitor;

import java.util.List;

import com.huaxia.opas.domain.monitor.Monitor3rdLog;

public interface Monitor3rdLogDao {
	
	List<Monitor3rdLog> queryMonitor3rdLogList(Monitor3rdLog monitor3rdLog, int curNum, int pageNum);
	
	int queryMonitor3rdLogCount(Monitor3rdLog monitor3rdLog);

}
