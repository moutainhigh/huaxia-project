package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLKeywordsDetailDao;
import com.huaxia.opas.domain.AMLKeywordsDetail;
import com.huaxia.opas.service.AMLKeywordsDetailService;

@Service("keywordsDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLKeywordsDetailServiceImpl implements AMLKeywordsDetailService {

	@Autowired
	private AMLKeywordsDetailDao keywordsDetailDao;

	@Override
	public int save(AMLKeywordsDetail keywordsDetail) {
		return getKeywordsDetailDao().insert(keywordsDetail);
	}

	public AMLKeywordsDetailDao getKeywordsDetailDao() {
		return keywordsDetailDao;
	}

	public void setKeywordsDetailDao(AMLKeywordsDetailDao keywordsDetailDao) {
		this.keywordsDetailDao = keywordsDetailDao;
	}

}
