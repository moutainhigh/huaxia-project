package com.huaxia.opas.dao.avaya.impl;


import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.avaya.AvayaLogDao;
import com.huaxia.opas.domain.avaya.AvayaLog;

public class AvayaLogDaoImpl extends AbstractDAO implements AvayaLogDao {

	private static final long serialVersionUID = -7665323062573510738L;
	private static final String NAMESPACES = "AvayaLog.";

	@Override
	public int add(AvayaLog avayaLog) {
		// TODO Auto-generated method stub
		return getSqlMap().insert(NAMESPACES+"addAvayaLog", avayaLog);
	}



}