package com.huaxia.opas.service.tripartite.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.AntDao;
import com.huaxia.opas.dao.tripartite.BizInpApplC1SfDao;
import com.huaxia.opas.dao.tripartite.BizInpApplC2SfDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.AntService;
import com.huaxia.opas.service.tripartite.SeaAirSfService;

/**
 *  第三方-海航与信审的交互
 * 
 * @author gaohui
 *
 */
public class SeaAirSfServiceImpl extends AbstractService implements SeaAirSfService , Serializable{

	private static final long serialVersionUID = -8407784358537022191L;

	@Resource(name = "bizInpApplC1SfDao")
	private BizInpApplC1SfDao bizInpApplC1SfDao;
	@Resource(name = "bizInpApplC2SfDao")
	private BizInpApplC2SfDao bizInpApplC2SfDao;
	public BizInpApplC1SfDao getBizInpApplC1SfDao() {
		return bizInpApplC1SfDao;
	}
	public void setBizInpApplC1SfDao(BizInpApplC1SfDao bizInpApplC1SfDao) {
		this.bizInpApplC1SfDao = bizInpApplC1SfDao;
	}
	public BizInpApplC2SfDao getBizInpApplC2SfDao() {
		return bizInpApplC2SfDao;
	}
	public void setBizInpApplC2SfDao(BizInpApplC2SfDao bizInpApplC2SfDao) {
		this.bizInpApplC2SfDao = bizInpApplC2SfDao;
	}
	@Override
	public void updateCardC1C2SeaMemberId(Map<String, Object> params) {
		
		bizInpApplC1SfDao.updateCardC1SeaMemberId(params);
		bizInpApplC2SfDao.updateCardC2SeaMemberId(params);
	}
	
}