package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLOrgDetailDao;
import com.huaxia.opas.domain.AMLOrgDetail;
import com.huaxia.opas.service.AMLOrgDetailService;

@Service("orgDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLOrgDetailServiceImpl implements AMLOrgDetailService {

	@Autowired
	private AMLOrgDetailDao orgDetailDao;

	@Override
	public int save(AMLOrgDetail orgDetail) {
		return getOrgDetailDao().insert(orgDetail);
	}

	public AMLOrgDetailDao getOrgDetailDao() {
		return orgDetailDao;
	}

	public void setOrgDetailDao(AMLOrgDetailDao orgDetailDao) {
		this.orgDetailDao = orgDetailDao;
	}

}
