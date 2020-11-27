package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpaspbocqueryreclmrecsumDao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opaspbocqueryreclmrecsum;

public class OpaspbocqueryreclmrecsumDaoImpl extends AbstractDAO implements OpaspbocqueryreclmrecsumDao ,Serializable{


	@Override
	public int insert(Opaspbocqueryreclmrecsum record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opaspbocqueryreclmrecsum> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList("Opaspbocqueryreclmrecsum.selectByExample", example);
	}


}
