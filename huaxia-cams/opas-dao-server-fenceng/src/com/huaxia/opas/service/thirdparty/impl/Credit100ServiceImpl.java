package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.thirdparty.Credit100Dao;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.Credit100Service;

/**
 * 百融服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class Credit100ServiceImpl extends AbstractService implements Credit100Service, Serializable {

	private static final long serialVersionUID = 4153360303281989645L;

	@Resource(name = "credit100Dao")
	private Credit100Dao credit100Dao;

	@Override
	public Map<String, String> selectSummaryInfo(String appId) {
		return getCredit100Dao().selectSummaryInfo(appId);
	}

	public Credit100Dao getCredit100Dao() {
		return credit100Dao;
	}

	public void setCredit100Dao(Credit100Dao credit100Dao) {
		this.credit100Dao = credit100Dao;
	}

	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getCredit100Dao().selectDetailInfo(appId);
	}
	
	@Override
	public Map<String,String> selectDetailAppId(String appId) {
		return getCredit100Dao().selectDetailAppId(appId);
	}

	@Override
	public Map<String, String> Query_biz_speciallist_cell_data(String appId) {
		return getCredit100Dao().Query_biz_speciallist_cell_data(appId);
	}

	@Override
	public Map<String, String> Query_OPAS_BIZ_SPECIALLIST_LM_DATA(String appId) {
		return getCredit100Dao().Query_OPAS_BIZ_SPECIALLIST_LM_DATA(appId);
	}

	@Override
	public Map<String, String> Query_OPAS_BIZ_CONSUMPTION_DATA(String appId) {
		return getCredit100Dao().Query_OPAS_BIZ_CONSUMPTION_DATA(appId);
	}

	@Override
	public List<String> Query_task_request_info(String appId) {
		return getCredit100Dao().Query_task_request_info(appId);
	}

	// add by qingfeng.xiu 20190218 17:40
	@Override
	public int Query_bairong_query_result(String appId) {
		return getCredit100Dao().Query_bairong_query_result(appId);
	}

}
