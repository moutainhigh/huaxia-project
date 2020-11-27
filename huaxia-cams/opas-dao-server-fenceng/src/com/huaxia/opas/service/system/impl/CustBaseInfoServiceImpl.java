package com.huaxia.opas.service.system.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huaxia.opas.dao.system.CustBaseInfoDao;
import com.huaxia.opas.domain.system.CustBaseInfo;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.CustBaseInfoService;

public class CustBaseInfoServiceImpl extends AbstractService implements
		CustBaseInfoService , Serializable{

	private static final long serialVersionUID = 2757582418545513970L;

	@Resource(name = "custBaseInfoDao")
	private CustBaseInfoDao custBaseInfoDao;

	@Override
	public CustBaseInfo selectByPrimaryKey(String autoId) throws Exception {
		return custBaseInfoDao.selectByPrimaryKey(autoId);
	}
}
