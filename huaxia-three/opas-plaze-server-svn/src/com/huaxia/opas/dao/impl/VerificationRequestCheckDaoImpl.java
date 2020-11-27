package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.VerificationRequestCheckDao;
import com.huaxia.opas.domain.VerificationRequestCheck;
import com.huaxia.opas.mapper.VerificationRequestCheckMapper;

@Repository
public class VerificationRequestCheckDaoImpl implements VerificationRequestCheckDao {

	@Resource
	private VerificationRequestCheckMapper pbocRequestCheckMapper;
	
	@Override
	public VerificationRequestCheck select() {
		return getPbocRequestCheckMapper().select();
	}

	@Override
	public int update() {
		return getPbocRequestCheckMapper().update();
	}

	public VerificationRequestCheckMapper getPbocRequestCheckMapper() {
		return pbocRequestCheckMapper;
	}

	public void setPbocRequestCheckMapper(VerificationRequestCheckMapper pbocRequestCheckMapper) {
		this.pbocRequestCheckMapper = pbocRequestCheckMapper;
	}

}
