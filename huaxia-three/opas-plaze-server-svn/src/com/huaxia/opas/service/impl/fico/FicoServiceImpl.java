package com.huaxia.opas.service.impl.fico;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.common.AppConst;
import com.huaxia.opas.dao.fico.FicoDao;
import com.huaxia.opas.dao.tripartite.TaskCallInfoDao;
import com.huaxia.opas.domain.fico.Fico;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.util.TaskStatusUtil;

@Service("ficoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FicoServiceImpl implements FicoService {
	private final static Logger logger = LoggerFactory.getLogger(FicoServiceImpl.class);
	@Autowired
	private FicoDao ficoDao;
	
	public FicoDao getFicoDao() {
		return ficoDao;
	}
	public void setFicoDao(FicoDao ficoDao) {
		this.ficoDao = ficoDao;
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
	public int getCountByAppId(String appId) {
		return ficoDao.getCountByAppId( appId);
	}

	@Override
	public int save(Fico fico) {
		return ficoDao.insert(fico);
	}

	@Override
	public Fico selectConfirmPboc(String appId) {
		return ficoDao.selectConfirmPboc(appId);
	}
	@Override
	public void saveFicoUpdateDataAdapterAction(Fico fico, String appId, String ficoTaskType) {
		ficoDao.insert(fico);
		taskCallInfoDao.updateSingleTask(appId, TaskStatusUtil.SUCCESS, ficoTaskType, null,
				TaskStatusUtil.CURR_USER, null, null);
	}
	@Override
	public Map<String, String> selectBizInpApplC1JudgeFlag(Map<String, String> params) {
		return ficoDao.selectBizInpApplC1JudgeFlag(params);
	}

}
