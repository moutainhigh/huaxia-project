package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsLoanGrntDao;
import com.huaxia.opas.domain.CRMSpIfsLoanGrnt;
import com.huaxia.opas.service.CRMSpIfsLoanGrntService;

@Service("spIfsLoanGrntService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsLoanGrntServiceImpl implements CRMSpIfsLoanGrntService {

	@Autowired
	private CRMSpIfsLoanGrntDao spIfsLoanGrntDao;

	@Override
	public int save(CRMSpIfsLoanGrnt spIfsLoanGrnt) {
		return getSpIfsLoanGrntDao().insert(spIfsLoanGrnt);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsLoanGrnt> spIfsLoanGrntList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsLoanGrntList);
		
		return getSpIfsLoanGrntDao().insertBatch(parameters);
	}

	public CRMSpIfsLoanGrntDao getSpIfsLoanGrntDao() {
		return spIfsLoanGrntDao;
	}

	public void setSpIfsLoanGrntDao(CRMSpIfsLoanGrntDao spIfsLoanGrntDao) {
		this.spIfsLoanGrntDao = spIfsLoanGrntDao;
	}

}
