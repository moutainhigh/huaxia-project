package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasbizspeciallistcelldataDao;
import com.huaxia.opas.domain.rule.Opasbizspeciallistcelldata;

public class OpasbizspeciallistcelldataDaoImpl extends AbstractDAO implements OpasbizspeciallistcelldataDao,Serializable {


	@Override
	public int insert(Opasbizspeciallistcelldata record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasbizspeciallistcelldata> selectByExample(Opasbizspeciallistcelldata example) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("Opasbizspeciallistcelldata.selectByExample", example);
	}


	@Override
	public List<Opasbizspeciallistcelldata> selectByAppid(String appId) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("Opasbizspeciallistcelldata.selectByAppid", appId);
	}


}
