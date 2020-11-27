package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCustindexDao;
import com.huaxia.opas.domain.CRMSpIfsCustindex;
import com.huaxia.opas.service.CRMSpIfsCustindexService;

@Service("spIfsCustindexService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCustindexServiceImpl implements CRMSpIfsCustindexService {

	@Autowired
	private CRMSpIfsCustindexDao spIfsCustindexDao;

	@Override
	public int save(CRMSpIfsCustindex spIfsCustindex) {
		return getSpIfsCustindexDao().insert(spIfsCustindex);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCustindex> spIfsCustindexList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCustindexList);
		
		return getSpIfsCustindexDao().insertBatch(parameters);
	}

	public CRMSpIfsCustindexDao getSpIfsCustindexDao() {
		return spIfsCustindexDao;
	}

	public void setSpIfsCustindexDao(CRMSpIfsCustindexDao spIfsCustindexDao) {
		this.spIfsCustindexDao = spIfsCustindexDao;
	}

}
