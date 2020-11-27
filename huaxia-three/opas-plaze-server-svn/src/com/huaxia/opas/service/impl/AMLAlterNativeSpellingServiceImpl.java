package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLAlterNativeSpellingDao;
import com.huaxia.opas.domain.AMLAlterNativeSpelling;
import com.huaxia.opas.service.AMLAlterNativeSpellingService;

@Service("alterNativeSpellingService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLAlterNativeSpellingServiceImpl implements AMLAlterNativeSpellingService {

	@Autowired
	private AMLAlterNativeSpellingDao alterNativeSpellingDao;

	@Override
	public int save(AMLAlterNativeSpelling alterNativeSpelling) {
		return getAlterNativeSpellingDao().insert(alterNativeSpelling);
	}

	public AMLAlterNativeSpellingDao getAlterNativeSpellingDao() {
		return alterNativeSpellingDao;
	}

	public void setAlterNativeSpellingDao(AMLAlterNativeSpellingDao alterNativeSpellingDao) {
		this.alterNativeSpellingDao = alterNativeSpellingDao;
	}

}
