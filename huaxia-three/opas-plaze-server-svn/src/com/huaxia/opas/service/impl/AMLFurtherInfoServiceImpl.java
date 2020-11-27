package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLFurtherInfoDao;
import com.huaxia.opas.domain.AMLFurtherInfo;
import com.huaxia.opas.service.AMLFurtherInfoService;

@Service("furtherInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLFurtherInfoServiceImpl implements AMLFurtherInfoService {

	@Autowired
	private AMLFurtherInfoDao furtherInfoDao;

	@Override
	public int save(AMLFurtherInfo furtherInfo) {
		return getFurtherInfoDao().insert(furtherInfo);
	}

	public AMLFurtherInfoDao getFurtherInfoDao() {
		return furtherInfoDao;
	}

	public void setFurtherInfoDao(AMLFurtherInfoDao furtherInfoDao) {
		this.furtherInfoDao = furtherInfoDao;
	}

}
