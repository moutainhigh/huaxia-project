package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCustInfoDao;
import com.huaxia.opas.domain.CRMSpIfsCustInfo;
import com.huaxia.opas.mapper.crm.CRMSpIfsCustInfoMapper;

@Repository
public class CRMSpIfsCustInfoDaoImpl implements CRMSpIfsCustInfoDao {
	
	@Resource
	private CRMSpIfsCustInfoMapper spIfsCustInfoMapper;

	@Override
	public int insert(CRMSpIfsCustInfo spIfsCustInfo) {
		return getSpIfsCustInfoMapper().insert(spIfsCustInfo);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCustInfoMapper().insertBatch(parameters);
	}

	public CRMSpIfsCustInfoMapper getSpIfsCustInfoMapper() {
		return spIfsCustInfoMapper;
	}

	public void setSpIfsCustInfoMapper(CRMSpIfsCustInfoMapper spIfsCustInfoMapper) {
		this.spIfsCustInfoMapper = spIfsCustInfoMapper;
	}

}
