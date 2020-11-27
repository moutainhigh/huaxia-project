package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpaspbocgruranteeDao;
import com.huaxia.opas.domain.rule.Opaspbocgrurantee;

public class OpaspbocgruranteeDaoIpml extends AbstractDAO implements OpaspbocgruranteeDao,Serializable {


	@Override
	public int insert(Opaspbocgrurantee record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opaspbocgrurantee> selectByExample(String example) {
		return getSqlMap().queryForList("Opaspbocgrurantee.selectByExample", example);
	}


}
