package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCPersonalInfoDao;
import com.huaxia.opas.mapper.PBOCPersonalInfoMapper;

@Repository
public class PBOCPersonalInfoDaoImpl implements PBOCPersonalInfoDao {

	@Resource
	private PBOCPersonalInfoMapper personalInfoMapper;

	public PBOCPersonalInfoMapper getPersonalInfoMapper() {
		return personalInfoMapper;
	}

	public void setPersonalInfoMapper(PBOCPersonalInfoMapper personalInfoMapper) {
		this.personalInfoMapper = personalInfoMapper;
	}

	@Override
	public int insert(Map<String, Object> entity) {
		return getPersonalInfoMapper().insert(entity);
	}

	@Override
	public int selectCountPbocByAppId(String appId) {
		return getPersonalInfoMapper().selectCountPbocByAppId(appId);
	}

	@Override
	public String selectLateAppIdByDayNameIdNo(Map<String, String> params) {
		return getPersonalInfoMapper().selectLateAppIdByDayNameIdNo(params);
	}

	@Override
	public Map<String, Object> saveClonePbocData(Map<String, Object> params) {
		return  getPersonalInfoMapper().saveClonePbocData(params);
	}

}
