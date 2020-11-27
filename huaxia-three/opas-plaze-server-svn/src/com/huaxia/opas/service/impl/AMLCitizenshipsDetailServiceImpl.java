package com.huaxia.opas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.aml.AMLCitizenshipsDetailDao;
import com.huaxia.opas.domain.AMLCitizenshipsDetail;
import com.huaxia.opas.service.AMLCitizenshipsDetailService;

@Service("citizenshipsDetailService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AMLCitizenshipsDetailServiceImpl implements AMLCitizenshipsDetailService {

	@Autowired
	private AMLCitizenshipsDetailDao citizenshipsDetailDao;

	@Override
	public int save(AMLCitizenshipsDetail citizenshipsDetail) {
		return getCitizenshipsDetailDao().insert(citizenshipsDetail);
	}

	public AMLCitizenshipsDetailDao getCitizenshipsDetailDao() {
		return citizenshipsDetailDao;
	}

	public void setCitizenshipsDetailDao(AMLCitizenshipsDetailDao citizenshipsDetailDao) {
		this.citizenshipsDetailDao = citizenshipsDetailDao;
	}

}
