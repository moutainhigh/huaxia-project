package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCybkBaseDao;
import com.huaxia.opas.domain.CRMSpIfsCybkBase;
import com.huaxia.opas.service.CRMSpIfsCybkBaseService;

@Service("spIfsCybkBaseService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCybkBaseServiceImpl implements CRMSpIfsCybkBaseService {

	@Autowired
	private CRMSpIfsCybkBaseDao spIfsCybkBaseDao;

	@Override
	public int save(CRMSpIfsCybkBase spIfsCybkBase) {
		return getSpIfsCybkBaseDao().insert(spIfsCybkBase);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCybkBase> spIfsCybkBaseList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCybkBaseList);
		
		return getSpIfsCybkBaseDao().insertBatch(parameters);
	}

	public CRMSpIfsCybkBaseDao getSpIfsCybkBaseDao() {
		return spIfsCybkBaseDao;
	}

	public void setSpIfsCybkBaseDao(CRMSpIfsCybkBaseDao spIfsCybkBaseDao) {
		this.spIfsCybkBaseDao = spIfsCybkBaseDao;
	}

}
