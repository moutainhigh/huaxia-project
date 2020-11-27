package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasPromoterRiskListDao;
import com.huaxia.opas.domain.rule.OpasPromoterRiskList;

public class OpasPromoterRiskListDaoImpl extends AbstractDAO implements OpasPromoterRiskListDao,Serializable {


	@Override
	public int deleteByPrimaryKey(String autoId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OpasPromoterRiskList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OpasPromoterRiskList> selectByExample(OpasPromoterRiskList example) {
		return getSqlMap().queryForList("OpasPromoterRiskList.selectByExample", example);
	}

	@Override
	public OpasPromoterRiskList selectByPrimaryKey(String autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpasPromoterRiskList> selectByPromoterNo(String promoterNo) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("OpasPromoterRiskList.selectByPromoterNo", promoterNo);
	}


}
