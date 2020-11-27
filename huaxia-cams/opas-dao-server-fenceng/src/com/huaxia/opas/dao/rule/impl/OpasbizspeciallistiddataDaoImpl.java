package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasbizspeciallistiddataDao;
import com.huaxia.opas.domain.rule.Opasbizspeciallistiddata;

public class OpasbizspeciallistiddataDaoImpl extends AbstractDAO implements OpasbizspeciallistiddataDao,Serializable {


	@Override
	public int insert(Opasbizspeciallistiddata record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasbizspeciallistiddata> selectByExample(Opasbizspeciallistiddata example) {
		return getSqlMap().queryForList("Opasbizspeciallistiddata.selectByExample", example);
	}


	@Override
	public List<Opasbizspeciallistiddata> selectByAppid(String appId) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("Opasbizspeciallistiddata.selectByAppid", appId);
	}


}
