package com.huaxia.opas.service.tripartite.impl;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.huaxia.opas.dao.log.LogRecordResultDao;
import com.huaxia.opas.dao.tripartite.SeaAirDao;
import com.huaxia.opas.dao.tripartite.TaskCallInfoDao;
import com.huaxia.opas.domain.tripartite.SeaAir;
import com.huaxia.opas.service.tripartite.SeaAirService;

@Service("seaAirService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SeaAirServiceImpl  implements SeaAirService {
	private final static Logger logger = LoggerFactory.getLogger(SeaAirServiceImpl.class);
	@Autowired
	private SeaAirDao seaAirDao;
	public SeaAirDao getSeaAirDao() {
		return seaAirDao;
	}
	public void setSeaAirDao(SeaAirDao seaAirDao) {
		this.seaAirDao = seaAirDao;
	}
	@Autowired
	private LogRecordResultDao logRecordResultDao;

	public LogRecordResultDao getLogRecordResultDao() {
		return logRecordResultDao;
	}

	public void setLogRecordResultDao(LogRecordResultDao logRecordResultDao) {
		this.logRecordResultDao = logRecordResultDao;
	}
	@Override
	public void save(SeaAir seaAir) {
		seaAirDao.insertSeaAirData(seaAir);
	}
	@Override
	public int getCountByAppId(String appId) {
		return seaAirDao.selectCountByAppId(appId);
	}
	@Autowired
	private TaskCallInfoDao taskCallInfoDao;
	
	public TaskCallInfoDao getTaskCallInfoDao() {
		return taskCallInfoDao;
	}
	public void setTaskCallInfoDao(TaskCallInfoDao taskCallInfoDao) {
		this.taskCallInfoDao = taskCallInfoDao;
	}
//	@Autowired
//	private TaskRequestInfoDao taskRequestInfoDao;
	@Override
	public void updateCardC1C2SeaMemberId(Map<String, Object> params) {
		seaAirDao.updateCardC1SeaMemberId(params);
		seaAirDao.updateCardC2SeaMemberId(params);
	}
	
	
}