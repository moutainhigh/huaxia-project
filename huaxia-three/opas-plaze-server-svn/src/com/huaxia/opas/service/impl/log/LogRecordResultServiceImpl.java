package com.huaxia.opas.service.impl.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.log.LogRecordResultDao;
import com.huaxia.opas.domain.log.LogRecordResult;
import com.huaxia.opas.service.log.LogRecordResultService;
import com.huaxia.opas.util.UUIDGen;

@Service("logRecordResultService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LogRecordResultServiceImpl implements LogRecordResultService {

	@Autowired
	private LogRecordResultDao logRecordResultDao;

	public LogRecordResultDao getLogRecordResultDao() {
		return logRecordResultDao;
	}

	public void setLogRecordResultDao(LogRecordResultDao logRecordResultDao) {
		this.logRecordResultDao = logRecordResultDao;
	}

	@Override
	public int save(LogRecordResult logRecordResult) {
		return getLogRecordResultDao().insert(logRecordResult);
	}

	@Override
	public int save(String appId, String moduleName, boolean flagDispose, String... dispose) {
		LogRecordResult logRecordResult = new LogRecordResult();
		logRecordResult.setUuid(UUIDGen.getUUID());
		logRecordResult.setAppId(appId);
		logRecordResult.setModuleName(moduleName);
		logRecordResult.setFlagDispose(flagDispose ? "0" : "1");
		
		// 记录原始报文响应信息
		if (dispose != null && dispose.length == 2) {
			logRecordResult.setDisposeResultCode(dispose[0]);
			logRecordResult.setDisposeResultDesc(dispose[1]);
		}
		return save(logRecordResult);
	}

}
