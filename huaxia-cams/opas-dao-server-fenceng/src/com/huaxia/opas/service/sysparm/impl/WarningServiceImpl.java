package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.WarningDao;
import com.huaxia.opas.domain.sysparm.Warning;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.WarningService;

public class WarningServiceImpl extends AbstractService implements WarningService,Serializable {

	private static final long serialVersionUID = 6753976246342517322L;
	
	//调用Dao层
	@Resource(name="warningDao")
	private WarningDao warningDao;
	
	public WarningDao getWarningListDao() {
		return warningDao;
	}

	public void setWarningListDao(WarningDao warningDao) {
		this.warningDao = warningDao;
	}

	//添加
	@Override
	public int insertWarningList(Warning warningList) throws CoreException {
		return getWarningListDao().insertWarningList(warningList);
	}

	//修改
	@Override
	public int updateWarning(Warning warning) throws CoreException {
		return getWarningListDao().updateWarning(warning);
	}

	//删除
	@Override
	public int deleteWarningList(String autoId) throws CoreException {
		return getWarningListDao().deleteWarningList(autoId);
	}


	//分页查询
	@Override
	public int queryWarningCount(Warning warning) {
		return getWarningListDao().queryWarningCount(warning);
	}

	@Override
	public List<Warning> queryWarningList(Warning warning, int curNum, int pageNum) {
		return getWarningListDao().queryWarningList(warning, curNum, pageNum);
	}


	
}
