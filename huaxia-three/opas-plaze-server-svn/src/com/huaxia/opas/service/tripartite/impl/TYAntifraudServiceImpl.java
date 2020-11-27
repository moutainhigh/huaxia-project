package com.huaxia.opas.service.tripartite.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.tripartite.TYAntifraudDao;
import com.huaxia.opas.domain.tripartite.TYAntifraud;
import com.huaxia.opas.domain.tripartite.TYAntifraudData;
import com.huaxia.opas.domain.tripartite.TYAntifraudRiskInfo;
import com.huaxia.opas.service.tripartite.TYAntifraudService;
import com.huaxia.opas.util.TaskStatusUtil;

@Service("tyAntifraudService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TYAntifraudServiceImpl  implements TYAntifraudService {
	private final static Logger logger = LoggerFactory.getLogger(TYAntifraudServiceImpl.class);
	@Autowired
	private TYAntifraudDao tyAntifraudDao;
	
	public TYAntifraudDao getTyAntifraudDao() {
		return tyAntifraudDao;
	}

	public void setTyAntifraudDao(TYAntifraudDao tyAntifraudDao) {
		this.tyAntifraudDao = tyAntifraudDao;
	}

	@Override
	public int getCountByAppId(String appId) {
		return tyAntifraudDao.selectCountByAppId(appId);
	}
//	@Autowired
//	private TaskCallStatusDao taskCallStatusDao;
	
	@Override
	public void save(TYAntifraud tyAntifraud) {
		//保存天御分主表信息OPAS_TIANYU_ANTIFRAUD_DATA 
		TYAntifraudData tyAntifraudData=tyAntifraud.getTyAntifraudData();
		tyAntifraudDao.insertTyData(tyAntifraudData);
		List<TYAntifraudRiskInfo> listTYAntifraudRiskInfo=tyAntifraud.getListTYAntifraudRiskInfo();
		//保存天御分对应主表的风险码信息 OPAS_TIANYU_ANTIFRAUD_RISKINFO 
		if(listTYAntifraudRiskInfo!=null&&!listTYAntifraudRiskInfo.isEmpty()&&listTYAntifraudRiskInfo.size()>0){
			tyAntifraudDao.insertBatchTyRisk(listTYAntifraudRiskInfo);
		}
	}


	@Override
	public void saveTYAntiUpdateDataAdapterAction(TYAntifraud tyAntifraud, String appId,String tyTaskType) {
		//保存天御分主表信息OPAS_TIANYU_ANTIFRAUD_DATA 
		TYAntifraudData tyAntifraudData=tyAntifraud.getTyAntifraudData();
		tyAntifraudDao.insertTyData(tyAntifraudData);
		List<TYAntifraudRiskInfo> listTYAntifraudRiskInfo=tyAntifraud.getListTYAntifraudRiskInfo();
		//保存天御分对应主表的风险码信息 OPAS_TIANYU_ANTIFRAUD_RISKINFO 
		if(listTYAntifraudRiskInfo!=null&&!listTYAntifraudRiskInfo.isEmpty()&&listTYAntifraudRiskInfo.size()>0){
			tyAntifraudDao.insertBatchTyRisk(listTYAntifraudRiskInfo);
		}
	/*	Map<String, String> paramsUpdate = new HashMap<String, String>();
	    paramsUpdate.put("appId", appId);
	    paramsUpdate.put("status", TaskStatusUtil.SUCCESS);
	    paramsUpdate.put("taskType", tyTaskType);
	    paramsUpdate.put("errorCode", null);
	    paramsUpdate.put("lstOptUser", TaskStatusUtil.CURR_USER);
	    paramsUpdate.put("requestAddNum", null);
	    paramsUpdate.put("failureAddNum", null);*/
//		getTaskCallStatusDao().updateSingleTask(paramsUpdate);
	}
}