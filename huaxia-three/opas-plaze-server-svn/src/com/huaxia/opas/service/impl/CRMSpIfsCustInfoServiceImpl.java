package com.huaxia.opas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.CRMSpIfsCustInfoDao;
import com.huaxia.opas.domain.CRMSpIfsCustInfo;
import com.huaxia.opas.service.CRMSpIfsCustInfoService;

@Service("spIfsCustInfoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CRMSpIfsCustInfoServiceImpl implements CRMSpIfsCustInfoService {

	@Autowired
	private CRMSpIfsCustInfoDao spIfsCustInfoDao;

	@Override
	public int save(CRMSpIfsCustInfo spIfsCustInfo) {
		return getSpIfsCustInfoDao().insert(spIfsCustInfo);
	}
	
	@Override
	public int saveBatch(List<CRMSpIfsCustInfo> spIfsCustInfoList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("list", spIfsCustInfoList);
		
		return getSpIfsCustInfoDao().insertBatch(parameters);
	}

	public CRMSpIfsCustInfoDao getSpIfsCustInfoDao() {
		return spIfsCustInfoDao;
	}

	public void setSpIfsCustInfoDao(CRMSpIfsCustInfoDao spIfsCustInfoDao) {
		this.spIfsCustInfoDao = spIfsCustInfoDao;
	}

}
