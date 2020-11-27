package com.huaxia.opas.dao.system;

import java.util.List;

import com.huaxia.opas.domain.system.OperationLog;

public interface OperationLogDao {

	public List<OperationLog> queryLogList(OperationLog operationlog, int curNum, int pageNum);

	public int queryLogCount(OperationLog operationlog);

}
