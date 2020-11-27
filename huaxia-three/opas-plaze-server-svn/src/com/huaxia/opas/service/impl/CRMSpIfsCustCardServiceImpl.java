package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCustCardDao;
import com.huaxia.opas.domain.CRMSpIfsCustCard;
import com.huaxia.opas.service.CRMSpIfsCustCardService;

@Service("spIfsCustCardService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCustCardServiceImpl implements CRMSpIfsCustCardService {

	@Autowired
	private CRMSpIfsCustCardDao spIfsCustCardDao;

	@Override
	public int save(CRMSpIfsCustCard spIfsCustCard) {
		return getSpIfsCustCardDao().insert(spIfsCustCard);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCustCard> spIfsCustCardList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCustCardList);
		
		return getSpIfsCustCardDao().insertBatch(parameters);
	}

	public CRMSpIfsCustCardDao getSpIfsCustCardDao() {
		return spIfsCustCardDao;
	}

	public void setSpIfsCustCardDao(CRMSpIfsCustCardDao spIfsCustCardDao) {
		this.spIfsCustCardDao = spIfsCustCardDao;
	}

}
