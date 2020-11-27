package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasbizapprovainfosuppDao;
import com.huaxia.opas.domain.rule.Opasbizapprovainfosupp;

public class OpasbizapprovainfosuppDaoImpl extends AbstractDAO implements OpasbizapprovainfosuppDao,Serializable {

	
	@Override
	public int deleteByPrimaryKey(String appId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Opasbizapprovainfosupp record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasbizapprovainfosupp> selectByExample(String appId) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("Opasbizapprovainfosupp.selectByExample",appId);
	}

	@Override
	public Opasbizapprovainfosupp selectByPrimaryKey(String appId) {
		// TODO Auto-generated method stub
		return null;
	}

	




}
