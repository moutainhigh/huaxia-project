package com.huaxia.opas.service.thirdparty.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.huaxia.opas.dao.thirdparty.PYPCRDao;
import com.huaxia.opas.service.thirdparty.PYPCRService;

public class PYPCRServiceImpl implements PYPCRService {

	@Autowired 
	public PYPCRDao pypcrDao;
	
	@Override
	public Map<String, Object> queryPYPCRBaseInfo(String appId) {
		return pypcrDao.selectPYPCRBaseInfo(appId);
	}

	@Override
	public Map<String, Object> queryPYPCRInfo(String appId) {
		return pypcrDao.selectPYPCRInfo(appId);
	}
	
	public Map<String, Object> queryPYPCR60Info(String appId) {
		return pypcrDao.selectPYPCR60Info(appId);
	}
	
	@Override
	public Map<String, Object> queryPYPCR60SyllogeInfo(String appId) {
		return pypcrDao.selectPYPCR60SyllogeInfo(appId);
	}
}
