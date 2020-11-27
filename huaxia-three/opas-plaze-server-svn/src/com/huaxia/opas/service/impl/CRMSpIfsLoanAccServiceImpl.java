package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsLoanAccDao;
import com.huaxia.opas.domain.CRMSpIfsLoanAcc;
import com.huaxia.opas.service.CRMSpIfsLoanAccService;

@Service("spIfsLoanAccService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsLoanAccServiceImpl implements CRMSpIfsLoanAccService {

	@Autowired
	private CRMSpIfsLoanAccDao spIfsLoanAccDao;

	@Override
	public int save(CRMSpIfsLoanAcc spIfsLoanAcc) {
		return getSpIfsLoanAccDao().insert(spIfsLoanAcc);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsLoanAcc> spIfsLoanAccList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsLoanAccList);
		
		return getSpIfsLoanAccDao().insertBatch(parameters);
	}

	public CRMSpIfsLoanAccDao getSpIfsLoanAccDao() {
		return spIfsLoanAccDao;
	}

	public void setSpIfsLoanAccDao(CRMSpIfsLoanAccDao spIfsLoanAccDao) {
		this.spIfsLoanAccDao = spIfsLoanAccDao;
	}

}
