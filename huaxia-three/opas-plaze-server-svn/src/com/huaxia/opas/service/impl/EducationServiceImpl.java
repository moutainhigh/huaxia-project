package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.EducationDao;
import com.huaxia.opas.domain.Education;
import com.huaxia.opas.service.EducationService;
import com.huaxia.opas.util.TaskStatusUtil;

@Service("educationService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class EducationServiceImpl  implements EducationService {
	private final static Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);
	@Autowired
	private EducationDao educationDao;
	
	@Override
	public int save(Education education) {
		return getEducationDao().insert(education);
	}
	
	@Override
	public int getCountByAppId(String appId) {
		return getEducationDao().selectCountByAppId(appId);
	}

	public EducationDao getEducationDao() {
		return educationDao;
	}

	public void setEducationDao(EducationDao educationDao) {
		this.educationDao = educationDao;
	}
//	@Autowired
//	private TaskCallStatusDao taskCallStatusDao;
//	
//	@Autowired
//	private TaskRequestInfoDao taskRequestInfoDao;
	
	@Override
	public void saveEducationUpdateDataAdapterAction(Education education, String appId, String taskType) {
		save(education);
/*		Map<String, String> paramsUpdate = new HashMap<String, String>();
	    paramsUpdate.put("appId", appId);
	    paramsUpdate.put("status", TaskStatusUtil.SUCCESS);
	    paramsUpdate.put("taskType", taskType);
	    paramsUpdate.put("errorCode", null);
	    paramsUpdate.put("lstOptUser", TaskStatusUtil.CURR_USER);
	    paramsUpdate.put("requestAddNum", null);
	    paramsUpdate.put("failureAddNum", null);*/
//		getTaskCallStatusDao().updateSingleTask(paramsUpdate);
	}

}
