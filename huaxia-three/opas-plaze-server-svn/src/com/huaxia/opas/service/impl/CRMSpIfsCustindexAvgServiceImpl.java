package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCustindexAvgDao;
import com.huaxia.opas.domain.CRMSpIfsCustindexAvg;
import com.huaxia.opas.service.CRMSpIfsCustindexAvgService;

@Service("spIfsCustindexAvgService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCustindexAvgServiceImpl implements CRMSpIfsCustindexAvgService {

	@Autowired
	private CRMSpIfsCustindexAvgDao spIfsCustindexAvgDao;

	@Override
	public int save(CRMSpIfsCustindexAvg spIfsCustindexAvg) {
		return getSpIfsCustindexAvgDao().insert(spIfsCustindexAvg);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCustindexAvg> spIfsCustindexAvgList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCustindexAvgList);
		
		return getSpIfsCustindexAvgDao().insertBatch(parameters);
	}

	public CRMSpIfsCustindexAvgDao getSpIfsCustindexAvgDao() {
		return spIfsCustindexAvgDao;
	}

	public void setSpIfsCustindexAvgDao(CRMSpIfsCustindexAvgDao spIfsCustindexAvgDao) {
		this.spIfsCustindexAvgDao = spIfsCustindexAvgDao;
	}

}
