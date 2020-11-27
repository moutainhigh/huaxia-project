package com.huaxia.opas.dao.monitor.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.monitor.Monitor3rdDao;
import com.huaxia.opas.domain.monitor.Monitor3rdList;

public class Monitor3rdDaoImpl extends AbstractDAO implements Monitor3rdDao {

	private static final long serialVersionUID = -2817285337982034138L;
	
	private static final String NAMESPACES = "Monitor3rdList.";

	@Override
	public List<Monitor3rdList> queryMonitor3rdList(Monitor3rdList monitor3rdList, int curNum, int pageNum) {
		
		List<Monitor3rdList> list = new ArrayList<Monitor3rdList>();
		
		list = getSqlMap().queryForList(NAMESPACES + "queryMonitor3rdList", monitor3rdList, curNum, pageNum);

		return list;
	}

	@Override
	public int queryMonitor3rdCount(Monitor3rdList monitor3rdList) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMonitor3rdCount", monitor3rdList);
	}

	@Override
	public Monitor3rdList selectById(String id) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectById", id);
	}

	//单条修改
	@Override
	public int updateByPrimaryKeySelective(Monitor3rdList monitor3rdList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", monitor3rdList);
	}

	//批量修改
	@Override
	public int updateStatusSelective(Monitor3rdList monitor3rdList) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", monitor3rdList);
	}
	
	

}
