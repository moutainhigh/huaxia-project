package com.huaxia.opas.service.compare.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.compare.EtcPadDao;
import com.huaxia.opas.service.compare.EtcPadService;

public class EtcPadServiceImpl implements EtcPadService, Serializable{

	private static final long serialVersionUID = -5706405943819964542L;

	@Resource(name = "etcPadDao")
	private EtcPadDao etcPadDao;
	
	
	@Override
	public Map<String, String> querySignatureAndIdByAppId(String appId) throws CoreException {
		
		return etcPadDao.selectSignatureAndIdByAppId(appId);
	}

}
