package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PoliceGatherDao;
import com.huaxia.opas.mapper.PoliceGatherMapper;

@Repository
public class PoliceGatherDaoImpl implements PoliceGatherDao {
	
	@Resource
	private PoliceGatherMapper policeGatherMapper;

	@Override
	public int insert(Map<String, Object> entity) {
		return getPoliceGatherMapper().insert(entity);
	}
	
	@Override
	public int insertFailure(Map<String, Object> entity) {
		return getPoliceGatherMapper().insertFailure(entity);
	}
	
	@Override
	public int selectCountByAppId(String appId) {
		return getPoliceGatherMapper().selectCountByAppId(appId);
	}
	
	@Override
	public int selectCountByCertNo(String certNo) {
		return getPoliceGatherMapper().selectCountByCertNo(certNo);
	}

	public PoliceGatherMapper getPoliceGatherMapper() {
		return policeGatherMapper;
	}

	public void setPoliceGatherMapper(PoliceGatherMapper policeGatherMapper) {
		this.policeGatherMapper = policeGatherMapper;
	}

}
