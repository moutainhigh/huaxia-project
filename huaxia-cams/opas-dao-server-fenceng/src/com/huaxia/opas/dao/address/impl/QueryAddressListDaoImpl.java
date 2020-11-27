package com.huaxia.opas.dao.address.impl;

import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.address.QueryAddressListDao;
import com.huaxia.opas.domain.address.OpasArea;
import com.huaxia.opas.domain.address.OpasCity;
import com.huaxia.opas.domain.address.OpasProvince;
import com.huaxia.opas.domain.address.OpasTown;

public class QueryAddressListDaoImpl extends AbstractDAO implements QueryAddressListDao{

	
	private static final String NAMESPACES = "addressLev.";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5901899476479640177L;

	@Override
	public List<OpasProvince> queryProvinceDetail() {
		// TODO Auto-generated method stub
		String CurrStatus="1";
		return getSqlMap().queryForList(NAMESPACES + "queryProvinceDetail", CurrStatus);
	}

	@Override
	public List<OpasCity> queryCityDetail() {
		// TODO Auto-generated method stub
		String CurrStatus="1";
		return getSqlMap().queryForList(NAMESPACES + "queryCityDetail", CurrStatus);
	}

	@Override
	public List<OpasArea> queryAreaDetail() {
		String CurrStatus="1";
		return getSqlMap().queryForList(NAMESPACES + "queryAreaDetail", CurrStatus);
	}

	@Override
	public List<OpasTown> queryTownDetail() {
		String CurrStatus="1";
		return getSqlMap().queryForList(NAMESPACES + "queryTownDetail", CurrStatus);
	}

}
