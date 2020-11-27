package com.huaxia.opas.service.system;

import java.util.List;

import com.huaxia.opas.domain.system.OperationLog;

public interface OperationLogService {

	public List<OperationLog> queryLogList(OperationLog operationlog,
			int curNum, int pageNum);

	public int queryLogCount(OperationLog operationlog);

}
