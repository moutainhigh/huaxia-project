package com.huaxia.opas.service.monitor;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.monitor.Monitor3rdList;

public interface Monitor3rdService {
	
	List<Monitor3rdList> queryMonitor3rdList(Monitor3rdList monitor3rdList, int curNum, int pageNum);

  	int queryMonitor3rdCount(Monitor3rdList monitor3rdList);
  	
  	Monitor3rdList selectById(String id) throws CoreException;
  	
  	int updateByPrimaryKeySelective(Monitor3rdList monitor3rdList) throws CoreException;
  	
  	int updateStatusSelective(Monitor3rdList monitor3rdList) throws CoreException;

}
