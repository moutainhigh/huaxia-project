package com.huaxia.opas.service.address.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.address.QueryAddressListDao;
import com.huaxia.opas.domain.address.OpasArea;
import com.huaxia.opas.domain.address.OpasCity;
import com.huaxia.opas.domain.address.OpasProvince;
import com.huaxia.opas.domain.address.OpasTown;
import com.huaxia.opas.service.address.QueryAddressListService;

public class QueryAddressListServiceImpl implements QueryAddressListService,Serializable{

	@Resource(name = "queryAddressLev")
	private QueryAddressListDao queryAddressLev;

	@Override
	public int save(Object t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Object t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object t) throws CoreException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object get(Object t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpasProvince> queryProvinceDetail() {
		// TODO Auto-generated method stub
		return queryAddressLev.queryProvinceDetail();
	}

	@Override
	public List<OpasCity> queryCityDetail() {
		// TODO Auto-generated method stub
		return queryAddressLev.queryCityDetail();
	}

	@Override
	public List<OpasArea> queryAreaDetail() {
		// TODO Auto-generated method stub
		return queryAddressLev.queryAreaDetail();
	}

	@Override
	public List<OpasTown> queryTownDetail() {
		// TODO Auto-generated method stub
		return queryAddressLev.queryTownDetail();
	}

	public QueryAddressListDao getQueryAddressLev() {
		return queryAddressLev;
	}

	public void setQueryAddressLev(QueryAddressListDao queryAddressLev) {
		this.queryAddressLev = queryAddressLev;
	}

}
