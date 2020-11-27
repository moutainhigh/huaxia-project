package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsPyrlDao;
import com.huaxia.opas.domain.CRMSpIfsPyrl;
import com.huaxia.opas.service.CRMSpIfsPyrlService;

@Service("spIfsPyrlService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsPyrlServiceImpl implements CRMSpIfsPyrlService {

	@Autowired
	private CRMSpIfsPyrlDao spIfsPyrlDao;

	@Override
	public int save(CRMSpIfsPyrl spIfsPyrl) {
		return getSpIfsPyrlDao().insert(spIfsPyrl);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsPyrl> spIfsPyrlList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsPyrlList);
		
		return getSpIfsPyrlDao().insertBatch(parameters);
	}

	public CRMSpIfsPyrlDao getSpIfsPyrlDao() {
		return spIfsPyrlDao;
	}

	public void setSpIfsPyrlDao(CRMSpIfsPyrlDao spIfsPyrlDao) {
		this.spIfsPyrlDao = spIfsPyrlDao;
	}

}
