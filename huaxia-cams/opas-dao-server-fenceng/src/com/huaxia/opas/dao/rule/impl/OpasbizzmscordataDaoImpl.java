package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasbizzmscordataDao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opasbizzmscordata;

public class OpasbizzmscordataDaoImpl extends AbstractDAO implements OpasbizzmscordataDao,Serializable {


	@Override
	public int insert(Opasbizzmscordata record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasbizzmscordata> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList("Opasbizzmscordata.selectByExample", example);
	}


}
