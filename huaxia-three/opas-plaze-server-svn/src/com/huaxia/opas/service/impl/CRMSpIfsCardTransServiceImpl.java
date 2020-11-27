package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCardTransDao;
import com.huaxia.opas.domain.CRMSpIfsCardTrans;
import com.huaxia.opas.service.CRMSpIfsCardTransService;

@Service("spIfsCardTransService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCardTransServiceImpl implements CRMSpIfsCardTransService {

	@Autowired
	private CRMSpIfsCardTransDao spIfsCardTransDao;

	@Override
	public int save(CRMSpIfsCardTrans spIfsCardTrans) {
		return getSpIfsCardTransDao().insert(spIfsCardTrans);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCardTrans> spIfsCardTransList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCardTransList);
		
		return getSpIfsCardTransDao().insertBatch(parameters);
	}

	public CRMSpIfsCardTransDao getSpIfsCardTransDao() {
		return spIfsCardTransDao;
	}

	public void setSpIfsCardTransDao(CRMSpIfsCardTransDao spIfsCardTransDao) {
		this.spIfsCardTransDao = spIfsCardTransDao;
	}

}
