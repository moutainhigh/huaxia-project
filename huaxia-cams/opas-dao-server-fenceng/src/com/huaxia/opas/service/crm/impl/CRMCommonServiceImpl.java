package com.huaxia.opas.service.crm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.crm.CRMCommonDao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.crm.CRMCommonService;

public class CRMCommonServiceImpl  extends AbstractService implements CRMCommonService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2341560507840604116L;
	@Resource(name = "CRMCommonDao")
	private CRMCommonDao crmCommonDao;
	public CRMCommonDao getCrmCommonDao() {
		return crmCommonDao;
	}

	public void CRMCommonDao(CRMCommonDao crmCommonDao) {
		this.crmCommonDao = crmCommonDao;
	}
	@Override
	public Map<String, Object> queryCRMInfo(String appId) {
		return getCrmCommonDao().queryCRMInfo(appId);
	}

	@Override
	public List<Object> selectJiejikaDetailList(String appId) {
		return getCrmCommonDao().queryJiejikaInfo(appId);
	}

	@Override
	public List<Object> selectWangyinDetailList(String appId) {
		return getCrmCommonDao().queryWangyinInfo(appId);
	}

	@Override
	public List<Object> selectDanbaoDetailList(String appId) {
		return getCrmCommonDao().queryDanbaoInfo(appId);
	}

	@Override
	public List<Object> selectDaikuanDetailList(String appId) {
		return getCrmCommonDao().queryDaikuanInfo(appId);
	}

}
