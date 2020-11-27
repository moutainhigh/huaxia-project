package com.huaxia.opas.service.monitor;

import java.util.List;

import com.huaxia.opas.domain.monitor.Monitor3rdLog;

public interface Monitor3rdLogService {
	
	int queryMonitor3rdLogCount(Monitor3rdLog monitor3rdLog);
  	
  	List<Monitor3rdLog> queryMonitor3rdLogList(Monitor3rdLog monitor3rdLog, int curNum, int pageNum);

}
