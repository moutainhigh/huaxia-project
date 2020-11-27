package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsSignSmryTotalDao;
import com.huaxia.opas.domain.CRMSpIfsSignSmryTotal;
import com.huaxia.opas.service.CRMSpIfsSignSmryTotalService;

@Service("spIfsSignSmryTotalService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsSignSmryTotalServiceImpl implements CRMSpIfsSignSmryTotalService {

	@Autowired
	private CRMSpIfsSignSmryTotalDao spIfsSignSmryTotalDao;

	@Override
	public int save(CRMSpIfsSignSmryTotal spIfsSignSmryTotal) {
		return getSpIfsSignSmryTotalDao().insert(spIfsSignSmryTotal);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsSignSmryTotal> spIfsSignSmryTotalList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsSignSmryTotalList);
		
		return getSpIfsSignSmryTotalDao().insertBatch(parameters);
	}

	public CRMSpIfsSignSmryTotalDao getSpIfsSignSmryTotalDao() {
		return spIfsSignSmryTotalDao;
	}

	public void setSpIfsSignSmryTotalDao(CRMSpIfsSignSmryTotalDao spIfsSignSmryTotalDao) {
		this.spIfsSignSmryTotalDao = spIfsSignSmryTotalDao;
	}

}
