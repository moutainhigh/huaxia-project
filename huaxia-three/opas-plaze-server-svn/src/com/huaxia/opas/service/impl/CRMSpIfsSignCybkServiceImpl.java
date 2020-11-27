package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsSignCybkDao;
import com.huaxia.opas.domain.CRMSpIfsSignCybk;
import com.huaxia.opas.service.CRMSpIfsSignCybkService;

@Service("spIfsSignCybkService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsSignCybkServiceImpl implements CRMSpIfsSignCybkService {

	@Autowired
	private CRMSpIfsSignCybkDao spIfsSignCybkDao;

	@Override
	public int save(CRMSpIfsSignCybk spIfsSignCybk) {
		return getSpIfsSignCybkDao().insert(spIfsSignCybk);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsSignCybk> spIfsSignCybkList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsSignCybkList);
		
		return getSpIfsSignCybkDao().insertBatch(parameters);
	}

	public CRMSpIfsSignCybkDao getSpIfsSignCybkDao() {
		return spIfsSignCybkDao;
	}

	public void setSpIfsSignCybkDao(CRMSpIfsSignCybkDao spIfsSignCybkDao) {
		this.spIfsSignCybkDao = spIfsSignCybkDao;
	}

}
