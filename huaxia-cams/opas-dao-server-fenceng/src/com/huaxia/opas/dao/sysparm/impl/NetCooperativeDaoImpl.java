package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.NetCooperativeDao;
import com.huaxia.opas.domain.sysparm.Business;

public class NetCooperativeDaoImpl extends AbstractDAO implements NetCooperativeDao {

	private static final long serialVersionUID = 6660855127324511195L;
	
	private static final String NAMESPACES = "NetCooperative.";

	@Override
	public int queryBusinessCount(Business business) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBusinessCount",business);
	}

	@Override
	public List<Business> queryBusinessList(Business business, int curNum, int pageNum) {
		List<Business> list = new ArrayList<Business>();
		list = getSqlMap().queryForList(NAMESPACES + "queryBusinessForList", business,curNum,pageNum);
		return list;
	}

	@Override
	public int insertBusiness(Business business) {
		return getSqlMap().insert(NAMESPACES + "insertBusiness", business);
	}

	@Override
	public int updateBusiness(Business business) {
		return getSqlMap().update(NAMESPACES + "updateBusiness", business);
	}

	@Override
	public int deleteBusiness(Business business) {
		List<String> ids = business.getIds();
		return getSqlMap().delete(NAMESPACES + "deleteBusiness", ids);
	}

	@Override
	public int BusinesssetStatusOK(Business business) {
		return getSqlMap().update(NAMESPACES + "BusinesssetStatusOK", business);
	}

	@Override
	public int BusinesssetStatusNO(Business business) {
		return getSqlMap().update(NAMESPACES + "BusinesssetStatusNO", business);
	}

	@Override
	public Business queryBusinessOnly(Business business) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBusinessOnly", business);
	}

	@Override
	public int insertBusiness(List<Business> list) {
		return getSqlMap().insert(NAMESPACES + "insertBusinessList", list);
				
	}

	@Override
	public List<Map<String, String>> queryAllNet() {
		return getSqlMap().queryForList(NAMESPACES+"queryAllNet");
	}

}
