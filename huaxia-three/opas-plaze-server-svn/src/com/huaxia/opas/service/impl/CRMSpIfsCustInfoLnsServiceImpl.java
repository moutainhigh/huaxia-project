package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCustInfoLnsDao;
import com.huaxia.opas.domain.CRMSpIfsCustInfoLns;
import com.huaxia.opas.service.CRMSpIfsCustInfoLnsService;

@Service("spIfsCustInfoLnsService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCustInfoLnsServiceImpl implements CRMSpIfsCustInfoLnsService {

	@Autowired
	private CRMSpIfsCustInfoLnsDao spIfsCustInfoLnsDao;

	@Override
	public int save(CRMSpIfsCustInfoLns spIfsCustInfoLns) {
		return getSpIfsCustInfoLnsDao().insert(spIfsCustInfoLns);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCustInfoLns> spIfsCustInfoLnsList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCustInfoLnsList);
		
		return getSpIfsCustInfoLnsDao().insertBatch(parameters);
	}

	public CRMSpIfsCustInfoLnsDao getSpIfsCustInfoLnsDao() {
		return spIfsCustInfoLnsDao;
	}

	public void setSpIfsCustInfoLnsDao(CRMSpIfsCustInfoLnsDao spIfsCustInfoLnsDao) {
		this.spIfsCustInfoLnsDao = spIfsCustInfoLnsDao;
	}

}
