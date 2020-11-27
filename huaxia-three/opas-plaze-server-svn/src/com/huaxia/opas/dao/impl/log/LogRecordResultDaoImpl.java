package com.huaxia.opas.dao.impl.log;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.log.LogRecordResultDao;
import com.huaxia.opas.domain.log.LogRecordResult;
import com.huaxia.opas.mapper.log.LogRecordResultMapper;

@Repository
public class LogRecordResultDaoImpl implements LogRecordResultDao {
	
	@Resource
	private LogRecordResultMapper logRecordResultMapper;

	public LogRecordResultMapper getLogRecordResultMapper() {
		return logRecordResultMapper;
	}

	public void setLogRecordResultMapper(LogRecordResultMapper logRecordResultMapper) {
		this.logRecordResultMapper = logRecordResultMapper;
	}

	@Override
	public int insert(LogRecordResult logRecordResult) {
		return getLogRecordResultMapper().insert(logRecordResult);
	}

}
