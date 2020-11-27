package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.SimplifyPoliceDao;
import com.huaxia.opas.domain.SimplifyPolice;
import com.huaxia.opas.service.SimplifyPoliceService;
import com.huaxia.opas.util.TaskStatusUtil;

@Service("simplifyPoliceService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SimplifyPoliceServiceImpl  implements SimplifyPoliceService {
	private final static Logger logger = LoggerFactory.getLogger(SimplifyPoliceServiceImpl.class);
	@Autowired
	private SimplifyPoliceDao simplifyPoliceDao;

	@Override
	public int save(SimplifyPolice police) {
		return getSimplifyPoliceDao().insert(police);
	}

	@Override
	public int getCountByAppId(String appId) {
		return getSimplifyPoliceDao().selectCountByAppId(appId);
	}
	
	@Override
	public int getCountByCertNo(String certNo) {
		return getSimplifyPoliceDao().selectCountByCertNo(certNo);
	}

	public SimplifyPoliceDao getSimplifyPoliceDao() {
		return simplifyPoliceDao;
	}

	public void setSimplifyPoliceDao(SimplifyPoliceDao simplifyPoliceDao) {
		this.simplifyPoliceDao = simplifyPoliceDao;
	}
//	@Autowired
//	private TaskCallStatusDao taskCallStatusDao;
//	
//	@Autowired
//	private TaskRequestInfoDao taskRequestInfoDao;
	
	@Override
	public void saveSimPoliceUpdateDataAdapterAction(SimplifyPolice simplifyPolice,String appId) {
	simplifyPoliceDao.insert(simplifyPolice);
	// 更新申请件任务状态
/*	Map<String, String> paramsUpdate = new HashMap<String, String>();
    paramsUpdate.put("appId", appId);
    paramsUpdate.put("status", TaskStatusUtil.SUCCESS);
//    paramsUpdate.put("taskType", Type.POLICE.getTypeId());
    paramsUpdate.put("errorCode", null);
    paramsUpdate.put("lstOptUser", TaskStatusUtil.CURR_USER);
    paramsUpdate.put("requestAddNum", null);
    paramsUpdate.put("failureAddNum", null);
//	getTaskCallStatusDao().updateSingleTask(paramsUpdate);
    */
	}
	
}