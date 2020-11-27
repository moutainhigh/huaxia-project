package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.ApplyLifeCicleDao;
import com.huaxia.opas.domain.ApplyLifeCicle;
import com.huaxia.opas.service.ApplyLifeCicleService;

@Service("applyLifeCicleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ApplyLifeCicleServiceImpl implements ApplyLifeCicleService {
	@Autowired
	private ApplyLifeCicleDao applyLifeCicleDao;
	
	public ApplyLifeCicleDao getApplyLifeCicleDao() {
		return applyLifeCicleDao;
	}
	
	public void setApplyLifeCicleDao(ApplyLifeCicleDao applyLifeCicleDao) {
		this.applyLifeCicleDao = applyLifeCicleDao;
	}


	@Override
	public void  insertApplyLifeCicle(ApplyLifeCicle applyLifeCicle) {
		applyLifeCicleDao.insertApplyLifeCicle(applyLifeCicle);
	}


}