package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsLoanAccAvgDao;
import com.huaxia.opas.domain.CRMSpIfsLoanAccAvg;
import com.huaxia.opas.service.CRMSpIfsLoanAccAvgService;

@Service("spIfsLoanAccAvgService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsLoanAccAvgServiceImpl implements CRMSpIfsLoanAccAvgService {

	@Autowired
	private CRMSpIfsLoanAccAvgDao spIfsLoanAccAvgDao;

	@Override
	public int save(CRMSpIfsLoanAccAvg spIfsLoanAccAvg) {
		return getSpIfsLoanAccAvgDao().insert(spIfsLoanAccAvg);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsLoanAccAvg> spIfsLoanAccAvgList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsLoanAccAvgList);
		
		return getSpIfsLoanAccAvgDao().insertBatch(parameters);
	}

	public CRMSpIfsLoanAccAvgDao getSpIfsLoanAccAvgDao() {
		return spIfsLoanAccAvgDao;
	}

	public void setSpIfsLoanAccAvgDao(CRMSpIfsLoanAccAvgDao spIfsLoanAccAvgDao) {
		this.spIfsLoanAccAvgDao = spIfsLoanAccAvgDao;
	}

}
