package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.OperationLogDao;
import com.huaxia.opas.domain.system.OperationLog;

public class OperationLogDaoImpl extends AbstractDAO implements OperationLogDao {

	private static final long serialVersionUID = -2786327678357864632L;

	private static final String NAMESPACES = "OperationLog.";

	@Override
	public List<OperationLog> queryLogList(OperationLog operationlog, int curNum, int pageNum) {
		List<OperationLog> list = new ArrayList<OperationLog>();
		list = getSqlMap().queryForList(NAMESPACES + "queryLogList", operationlog, curNum, pageNum);
		return list;
	}

	@Override
	public int queryLogCount(OperationLog operationlog) {
		return getSqlMap().queryForObject(NAMESPACES + "queryLogCount", operationlog);
	}

}
