package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsLoanDtlDao;
import com.huaxia.opas.domain.CRMSpIfsLoanDtl;
import com.huaxia.opas.service.CRMSpIfsLoanDtlService;

@Service("spIfsLoanDtlService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsLoanDtlServiceImpl implements CRMSpIfsLoanDtlService {

	@Autowired
	private CRMSpIfsLoanDtlDao spIfsLoanDtlDao;

	@Override
	public int save(CRMSpIfsLoanDtl spIfsLoanDtl) {
		return getSpIfsLoanDtlDao().insert(spIfsLoanDtl);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsLoanDtl> spIfsLoanDtlList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsLoanDtlList);
		
		return getSpIfsLoanDtlDao().insertBatch(parameters);
	}

	public CRMSpIfsLoanDtlDao getSpIfsLoanDtlDao() {
		return spIfsLoanDtlDao;
	}

	public void setSpIfsLoanDtlDao(CRMSpIfsLoanDtlDao spIfsLoanDtlDao) {
		this.spIfsLoanDtlDao = spIfsLoanDtlDao;
	}

}
