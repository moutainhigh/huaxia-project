package com.huaxia.opas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.dao.ZMXYIvsScoreDao;
import com.huaxia.opas.domain.ZMXYIvsScore;
import com.huaxia.opas.domain.ZMXYIvsScore.IvsDetail;
import com.huaxia.opas.service.ZMXYIvsScoreService;

@Service("ivsScoreService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ZMXYIvsScoreServiceImpl implements ZMXYIvsScoreService {

	@Autowired
	private ZMXYIvsScoreDao ivsScoreDao;
	public ZMXYIvsScoreDao getIvsScoreDao() {
		return ivsScoreDao;
	}
	public void setIvsScoreDao(ZMXYIvsScoreDao ivsScoreDao) {
		this.ivsScoreDao = ivsScoreDao;
	}
	
	@Override
	public int save(ZMXYIvsScore ivsScore) {
		int result = 0;
		
		// "行转列"方式
		// 通过增加字段列方式实现
		List<IvsDetail> ivsDetailList = ivsScore.getIvsDetailList();
		if (ivsDetailList != null && ivsDetailList.size() > 0) {
			for (IvsDetail detail : ivsDetailList) {
				String factorCode = detail.getCode();
				if (factorCode.contains("CERTNO")) {
					ivsScore.setCertNoRiskFactorCode(factorCode);
					ivsScore.setCertNoRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("NAME")) {
					ivsScore.setNameRiskFactorCode(factorCode);
					ivsScore.setNameRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("EMAIL")) {
					ivsScore.setEmailRiskFactorCode(factorCode);
					ivsScore.setEmailRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("PHONE")) {
					ivsScore.setPhoneRiskFactorCode(factorCode);
					ivsScore.setPhoneRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("BANKCARD")) {
					ivsScore.setBankCardRiskFactorCode(factorCode);
					ivsScore.setBankCardRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("ADDR")) {
					ivsScore.setAddrRiskFactorCode(factorCode);
					ivsScore.setAddrRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("IP")) {
					ivsScore.setIpRiskFactorCode(factorCode);
					ivsScore.setIpRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("IMSI")) {
					ivsScore.setImsiRiskFactorCode(factorCode);
					ivsScore.setImsiRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("IMEI")) {
					ivsScore.setImeiRiskFactorCode(factorCode);
					ivsScore.setImeiRiskFactorDesc(detail.getDescription());
					continue;
				}
				
				if (factorCode.contains("MAC")) {
					// WIFI-MAC
					if (factorCode.contains("WIFI")) {
						ivsScore.setWifiMacRiskFactorCode(factorCode);
						ivsScore.setWifiMacRiskFactorDesc(detail.getDescription());
					} 
					// MAC
					else {
						ivsScore.setMacRiskFactorCode(factorCode);
						ivsScore.setMacRiskFactorDesc(detail.getDescription());
					}
					continue;
				}
			}
		}
		
		result += getIvsScoreDao().insert(ivsScore);
		return result;
	}

	@Override
	public int queryCountZMXYIvsScoreByAppId(String appId) {

		return getIvsScoreDao().selectCountZMXYIvsScoreByAppId(appId);
	}

}
