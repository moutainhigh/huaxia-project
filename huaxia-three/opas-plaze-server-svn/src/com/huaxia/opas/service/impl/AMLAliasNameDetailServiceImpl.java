package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLAliasNameDetailDao;
import com.huaxia.opas.domain.AMLAliasNameDetail;
import com.huaxia.opas.service.AMLAliasNameDetailService;

@Service("aliasNameDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLAliasNameDetailServiceImpl implements AMLAliasNameDetailService {

	@Autowired
	private AMLAliasNameDetailDao aliasNameDetailDao;

	@Override
	public int save(AMLAliasNameDetail aliasNameDetail) {
		return getAliasNameDetailDao().insert(aliasNameDetail);
	}

	public AMLAliasNameDetailDao getAliasNameDetailDao() {
		return aliasNameDetailDao;
	}

	public void setAliasNameDetailDao(AMLAliasNameDetailDao aliasNameDetailDao) {
		this.aliasNameDetailDao = aliasNameDetailDao;
	}

}
