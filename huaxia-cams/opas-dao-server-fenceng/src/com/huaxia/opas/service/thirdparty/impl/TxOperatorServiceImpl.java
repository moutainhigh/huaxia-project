package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.dao.thirdparty.TxOperatorDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.TxOperatorService;

/**
 * 腾讯天御分
 * 
 * @author gaoh
 *
 */
public class TxOperatorServiceImpl extends AbstractService implements TxOperatorService , Serializable{
	
	private static final long serialVersionUID = 6533499563670781012L;
	
	@Resource(name = "txOperatorDao")
	private TxOperatorDao txOperatorDao;
	
	public TxOperatorDao getTxOperatorDao() {
		return txOperatorDao;
	}

	public void setTxOperatorDao(TxOperatorDao txOperatorDao) {
		this.txOperatorDao = txOperatorDao;
	}
	
	@Resource(name = "policeDao")
	private PoliceDao policeDao;
	
	@Override
	public Map<String, String> queryTxOperSummaryInfoByAppId(String appId) {
		
		return  txOperatorDao.selectTxOperSummaryInfoByAppId(appId);
		
	}

	@Override
	public Map<String, String> queryUnicomAddressInfoByAppId(String appId) {
		return txOperatorDao.selectUnicomAddressInfoByAppId(appId);
	}

	@Override
	public List<Map<String, String>> queryUnicomListMessage(String appId) {
		return txOperatorDao.selectUnicomListMessage(appId);
	}

	@Override
	public Map<String, String> querySfrzBaseData(String appId) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> sfrz = new HashMap<String, String>();
		String authResult = "";
		Map<String, String>  c1Map = policeDao.selectC1Idtype(appId);
		String c1c2Flag = c1Map.get("c1c2Flag");
		//1主附同申 2单独申请附卡3单独申请主卡
		if(("1".equals(c1c2Flag)||"3".equals(c1c2Flag))){
			map.put("appId", appId);
			map.put("cardFlag", "0");
			authResult = txOperatorDao.querySfrzBaseData(map);
			if(null!=authResult){
				sfrz.put("authResult", authResult);
			}
			
			
		}else if ("2".equals(c1c2Flag)){
			map.put("appId", appId);
			map.put("cardFlag", "1");
			authResult = txOperatorDao.querySfrzBaseData(map);
			if(null!=authResult){
				sfrz.put("authResultF", authResult);
			}
			
			
		}
		return sfrz;
	}

	@Override
	public Map<String, String> queryNbBaseInfo(String appId) {
		return txOperatorDao.queryNbBaseInfo(appId);
	}

}