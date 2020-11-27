package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpaspbocoverdueandfellbackDao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opaspbocoverdueandfellback;

public class OpaspbocoverdueandfellbackDaoImpl extends AbstractDAO implements OpaspbocoverdueandfellbackDao,Serializable {


	@Override
	public int insert(Opaspbocoverdueandfellback record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opaspbocoverdueandfellback> selectByExample(Opasbizinpapplc1 appId) {
		return getSqlMap().queryForList("Opaspbocoverdueandfellback.selectByExample", appId);
	}


}
