package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLLinkPersonDao;
import com.huaxia.opas.domain.AMLLinkPerson;
import com.huaxia.opas.service.AMLLinkPersonService;

@Service("linkPersonService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLLinkPersonServiceImpl implements AMLLinkPersonService {

	@Autowired
	private AMLLinkPersonDao AMLLinkPersonDao;

	@Override
	public int save(AMLLinkPerson linkPerson) {
		return getAMLLinkPersonDao().insert(linkPerson);
	}

	public AMLLinkPersonDao getAMLLinkPersonDao() {
		return AMLLinkPersonDao;
	}

	public void setAMLLinkPersonDao(AMLLinkPersonDao aMLLinkPersonDao) {
		AMLLinkPersonDao = aMLLinkPersonDao;
	}

}
