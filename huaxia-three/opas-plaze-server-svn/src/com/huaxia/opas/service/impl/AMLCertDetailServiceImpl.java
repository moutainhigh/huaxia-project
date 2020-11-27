package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLCertDetailDao;
import com.huaxia.opas.domain.AMLCertDetail;
import com.huaxia.opas.service.AMLCertDetailService;

@Service("certDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLCertDetailServiceImpl implements AMLCertDetailService {

	@Autowired
	private AMLCertDetailDao certDetailDao;

	@Override
	public int save(AMLCertDetail certDetail) {
		return getCertDetailDao().insert(certDetail);
	}

	public AMLCertDetailDao getCertDetailDao() {
		return certDetailDao;
	}

	public void setCertDetailDao(AMLCertDetailDao certDetailDao) {
		this.certDetailDao = certDetailDao;
	}

}
