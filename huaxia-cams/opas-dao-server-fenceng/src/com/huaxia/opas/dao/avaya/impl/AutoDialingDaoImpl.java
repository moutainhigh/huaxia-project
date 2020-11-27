package com.huaxia.opas.dao.avaya.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.avaya.AutoDialingDao;
import com.huaxia.opas.domain.avaya.Avaya;

public class AutoDialingDaoImpl extends AbstractDAO implements AutoDialingDao{

	/**
	 *
	 */
	private static final long serialVersionUID = 2406687494209297143L;
	
	private static final String NAMESPACES = "AutoDialing.";

	@Override
	public int add(Avaya avaya) {
		return getSqlMap().insert(NAMESPACES + "addUser", avaya);
	}

	@Override
	public int del(String avaya) {
		return getSqlMap().delete(NAMESPACES + "delUser", avaya);
	}

	@Override
	public int update(Avaya avaya) {
		return getSqlMap().update(NAMESPACES + "updateUser", avaya);
	}


	@Override
	public List<Avaya> queryAll() {
		return getSqlMap().queryForObject(NAMESPACES + "findAll");
	}

	@Override
	public int queryAvayaAutoDialingLimitCount(Avaya avaya) {
		return getSqlMap().queryForObject(NAMESPACES + "queryAvayaAutoDialingLimitCount",avaya);
	}

	@Override
	public List<Avaya> queryAvayaAutoDialingLimit(Avaya avaya, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryAvayaAutoDialingLimit", avaya, curNum, pageNum);
	}

}
