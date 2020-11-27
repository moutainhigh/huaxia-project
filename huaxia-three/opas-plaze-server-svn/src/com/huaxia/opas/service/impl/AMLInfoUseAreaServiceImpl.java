package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLInfoUseAreaDao;
import com.huaxia.opas.domain.AMLInfoUseArea;
import com.huaxia.opas.service.AMLInfoUseAreaService;

@Service("infoUseAreaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLInfoUseAreaServiceImpl implements AMLInfoUseAreaService {

	@Autowired
	private AMLInfoUseAreaDao infoUseAreaDao;

	@Override
	public int save(AMLInfoUseArea infoUseArea) {
		return getInfoUseAreaDao().insert(infoUseArea);
	}

	public AMLInfoUseAreaDao getInfoUseAreaDao() {
		return infoUseAreaDao;
	}

	public void setInfoUseAreaDao(AMLInfoUseAreaDao infoUseAreaDao) {
		this.infoUseAreaDao = infoUseAreaDao;
	}

}
