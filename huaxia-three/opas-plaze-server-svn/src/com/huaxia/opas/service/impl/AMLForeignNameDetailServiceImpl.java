package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLForeignNameDetailDao;
import com.huaxia.opas.domain.AMLForeignNameDetail;
import com.huaxia.opas.service.AMLForeignNameDetailService;

@Service("foreignNameDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLForeignNameDetailServiceImpl implements AMLForeignNameDetailService {

	@Autowired
	private AMLForeignNameDetailDao foreignNameDetailDao;

	@Override
	public int save(AMLForeignNameDetail foreignNameDetail) {
		return getForeignNameDetailDao().insert(foreignNameDetail);
	}

	public AMLForeignNameDetailDao getForeignNameDetailDao() {
		return foreignNameDetailDao;
	}

	public void setForeignNameDetailDao(AMLForeignNameDetailDao foreignNameDetailDao) {
		this.foreignNameDetailDao = foreignNameDetailDao;
	}

}
