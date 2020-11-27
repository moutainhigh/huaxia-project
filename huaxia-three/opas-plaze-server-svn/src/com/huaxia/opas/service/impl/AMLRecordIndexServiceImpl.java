package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLRecordIndexDao;
import com.huaxia.opas.domain.AMLRecordIndex;
import com.huaxia.opas.service.AMLRecordIndexService;

@Service("recordIndexService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLRecordIndexServiceImpl implements AMLRecordIndexService {

	@Autowired
	private AMLRecordIndexDao recordIndexDao;

	@Override
	public int save(AMLRecordIndex recordIndex) {
		return getRecordIndexDao().insert(recordIndex);
	}

	public AMLRecordIndexDao getRecordIndexDao() {
		return recordIndexDao;
	}

	public void setRecordIndexDao(AMLRecordIndexDao recordIndexDao) {
		this.recordIndexDao = recordIndexDao;
	}

}
