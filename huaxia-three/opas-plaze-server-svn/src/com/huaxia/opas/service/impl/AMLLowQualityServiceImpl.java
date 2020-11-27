package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLLowQualityDao;
import com.huaxia.opas.domain.AMLLowQuality;
import com.huaxia.opas.service.AMLLowQualityService;

@Service("lowQualityService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLLowQualityServiceImpl implements AMLLowQualityService {

	@Autowired
	private AMLLowQualityDao lowQualityDao;

	@Override
	public int save(AMLLowQuality lowQuality) {
		return getLowQualityDao().insert(lowQuality);
	}

	public AMLLowQualityDao getLowQualityDao() {
		return lowQualityDao;
	}

	public void setLowQualityDao(AMLLowQualityDao lowQualityDao) {
		this.lowQualityDao = lowQualityDao;
	}

}
