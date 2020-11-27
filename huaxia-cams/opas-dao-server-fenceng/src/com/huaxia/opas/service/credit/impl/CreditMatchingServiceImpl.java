package com.huaxia.opas.service.credit.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.credit.CreditMatchingDao;
import com.huaxia.opas.domain.credit.CreditMatching;
import com.huaxia.opas.service.credit.CreditMatchingService;
/*******************************
 *@describe:人行征信查询匹配设置 service实现
 *@author：xiaoJianFeng
 *@date:2017-03-19
 ********************************/
public class CreditMatchingServiceImpl implements CreditMatchingService, Serializable{
	@Resource(name = "creditMatchingDao")
	private CreditMatchingDao creditMatchingDaoImpl;
	@Override
	public Map<String, Object> selectCreditMatchPage(CreditMatching creditMatching, int begNum, int pageNum) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String total = creditMatchingDaoImpl.selectCreditMatchCount(creditMatching);
		List<CreditMatching> rows = creditMatchingDaoImpl.selectCreditMatchPage(creditMatching, begNum, pageNum);
		dataMap.put("total", total);
		dataMap.put("rows", rows);
		return dataMap;	
	}

	@Override
	public void addCreditMatch(CreditMatching creditMatching) {
		creditMatchingDaoImpl.addCreditMatch(creditMatching);
		
	}

	@Override
	public void updateCreditMatch(CreditMatching creditMatching) {
		creditMatchingDaoImpl.updateCreditMatch(creditMatching);
		
	}

	@Override
	public void deleteCreditMatch(String autoId) {
		creditMatchingDaoImpl.deleteCreditMatch(autoId);
		
	}

}
