package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCrcdPrycrtDao;
import com.huaxia.opas.domain.CRMSpIfsCrcdPrycrt;
import com.huaxia.opas.service.CRMSpIfsCrcdPrycrtService;

@Service("spIfsCrcdPrycrtService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCrcdPrycrtServiceImpl implements CRMSpIfsCrcdPrycrtService {

	@Autowired
	private CRMSpIfsCrcdPrycrtDao spIfsCrcdPrycrtDao;

	@Override
	public int save(CRMSpIfsCrcdPrycrt spIfsCrcdPrycrt) {
		return getSpIfsCrcdPrycrtDao().insert(spIfsCrcdPrycrt);
	}

	public CRMSpIfsCrcdPrycrtDao getSpIfsCrcdPrycrtDao() {
		return spIfsCrcdPrycrtDao;
	}

	public void setSpIfsCrcdPrycrtDao(CRMSpIfsCrcdPrycrtDao spIfsCrcdPrycrtDao) {
		this.spIfsCrcdPrycrtDao = spIfsCrcdPrycrtDao;
	}

	@Override
	public int saveBatch(List<CRMSpIfsCrcdPrycrt> spIfsCrcdPrycrtList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCrcdPrycrtList);
		
		return getSpIfsCrcdPrycrtDao().insertBatch(parameters);
	}

}
