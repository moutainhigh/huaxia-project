package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLDobsDetailDao;
import com.huaxia.opas.domain.AMLDobsDetail;
import com.huaxia.opas.service.AMLDobsDetailService;

@Service("dobsDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLDobsDetailServiceImpl implements AMLDobsDetailService {

	@Autowired
	private AMLDobsDetailDao dobsDetailDao;

	@Override
	public int save(AMLDobsDetail dobsDetail) {
		return getDobsDetailDao().insert(dobsDetail);
	}

	public AMLDobsDetailDao getDobsDetailDao() {
		return dobsDetailDao;
	}

	public void setDobsDetailDao(AMLDobsDetailDao dobsDetailDao) {
		this.dobsDetailDao = dobsDetailDao;
	}

}
