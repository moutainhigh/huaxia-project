package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasbizzmcreditwatchlistiDao;
import com.huaxia.opas.domain.rule.Opasbizzmcreditwatchlisti;

public class OpasbizzmcreditwatchlistiDaoImpl extends AbstractDAO implements OpasbizzmcreditwatchlistiDao,Serializable {


	@Override
	public int insert(Opasbizzmcreditwatchlisti record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasbizzmcreditwatchlisti> selectByExample(Opasbizzmcreditwatchlisti example) {
		return getSqlMap().queryForList("Opasbizzmcreditwatchlisti.selectByExample", example);
	}


	@Override
	public List<Opasbizzmcreditwatchlisti> selectByAppId(String appId) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("Opasbizzmcreditwatchlisti.selectByAppId", appId);
	}


	@Override
	public List<String> selectAntHitMsg(String appId) {
		return getSqlMap().queryForList("Opasbizzmcreditwatchlisti.selectAntHitMsg", appId);
	}
}
