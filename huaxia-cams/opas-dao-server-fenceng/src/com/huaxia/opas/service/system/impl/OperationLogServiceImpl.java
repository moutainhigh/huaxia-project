package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.OperationLogDao;
import com.huaxia.opas.domain.system.OperationLog;
import com.huaxia.opas.service.system.OperationLogService;

public class OperationLogServiceImpl extends AbstractDAO implements
		OperationLogService, Serializable {

	private static final long serialVersionUID = -2786327678357864632L;

	@Resource(name = "operationLogDao")
	private OperationLogDao operationLogDao;

	@Override
	public List<OperationLog> queryLogList(OperationLog operationlog,
			int curNum, int pageNum) {
		return getOperationLogDao().queryLogList(operationlog, curNum, pageNum);
	}

	@Override
	public int queryLogCount(OperationLog operationlog) {
		return getOperationLogDao().queryLogCount(operationlog);
	}

	public OperationLogDao getOperationLogDao() {
		return operationLogDao;
	}

	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

}
