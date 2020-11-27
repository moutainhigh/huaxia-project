package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCustDebtDao;
import com.huaxia.opas.domain.CRMSpIfsCustDebt;
import com.huaxia.opas.service.CRMSpIfsCustDebtService;

@Service("spIfsCustDebtService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCustDebtServiceImpl implements CRMSpIfsCustDebtService {

	@Autowired
	private CRMSpIfsCustDebtDao spIfsCustDebtDao;

	@Override
	public int save(CRMSpIfsCustDebt spIfsCustDebt) {
		return getSpIfsCustDebtDao().insert(spIfsCustDebt);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCustDebt> spIfsCustDebtList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCustDebtList);
		
		return getSpIfsCustDebtDao().insertBatch(parameters);
	}

	public CRMSpIfsCustDebtDao getSpIfsCustDebtDao() {
		return spIfsCustDebtDao;
	}

	public void setSpIfsCustDebtDao(CRMSpIfsCustDebtDao spIfsCustDebtDao) {
		this.spIfsCustDebtDao = spIfsCustDebtDao;
	}

}
