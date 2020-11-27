package com.huaxia.opas.service.riskcheck.impl;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import com.huaxia.opas.dao.riskcheck.RiskCheckDao;
import com.huaxia.opas.domain.riskcheck.KeyfiledResultInfo;
import com.huaxia.opas.service.riskcheck.KeyfiledResultService;
import com.huaxia.opas.util.StringUtil;


public class KeyfiledResultServiceImpl implements KeyfiledResultService, Serializable {

	private static final long serialVersionUID = 7529815164223327068L;

	@Resource(name = "riskCheckDao")
	private RiskCheckDao riskCheckDao;

	@Override
	public void insertKeyfiledResult(String appId, String stakeholderType) throws Exception {
		String appid = appId.substring(0, 15);
		KeyfiledResultInfo keyfiledResultInfo = new KeyfiledResultInfo();
		keyfiledResultInfo.setAppId(appid);
		int result = riskCheckDao.Query_OPAS_KEYFILED_RESULTINFO(keyfiledResultInfo);
		keyfiledResultInfo.setAppId(appId);
		if(result>0){
			// 更新数据库记录
			riskCheckDao.update_KEYFILED_RESULTINFO(keyfiledResultInfo);
		}else{
			// 保存入库
			keyfiledResultInfo.setCrtDate(new Date());
			keyfiledResultInfo.setAutoId(StringUtil.randomUUIDPlainString());
			riskCheckDao.save_KEYFILED_RESULTINFO(keyfiledResultInfo);
		}
	}

}
