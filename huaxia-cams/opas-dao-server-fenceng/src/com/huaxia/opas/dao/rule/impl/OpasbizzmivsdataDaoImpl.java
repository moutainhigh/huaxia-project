package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasbizzmivsdataDao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opasbizzmivsdata;

public class OpasbizzmivsdataDaoImpl extends AbstractDAO implements OpasbizzmivsdataDao,Serializable  {


	@Override
	public int insert(Opasbizzmivsdata record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasbizzmivsdata> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList("Opasbizzmivsdata.selectByExample", example);
	}


}
