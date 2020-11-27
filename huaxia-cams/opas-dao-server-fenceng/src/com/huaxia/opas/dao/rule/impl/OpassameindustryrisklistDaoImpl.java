package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpassameindustryrisklistDao;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opassameindustryrisklist;

public class OpassameindustryrisklistDaoImpl extends AbstractDAO implements OpassameindustryrisklistDao,Serializable  {


	@Override
	public int insert(Opassameindustryrisklist record) {
		return 0;
	}


	@Override
	public List<Opassameindustryrisklist> selectByExample(Opasbizinpapplc1 example) {
		return getSqlMap().queryForList("Opassameindustryrisklist.selectByExample", example);
	}
	
	@Override
	public List<String> selectPhoneNameMatch(String appId) {
		return getSqlMap().queryForList("Opassameindustryrisklist.selectPhoneNameMatch", appId);
	}


	@Override
	public List<String> selectJdPhoneNameMatch(String appId) {
		return getSqlMap().queryForList("Opassameindustryrisklist.selectJdPhoneNameMatch", appId);
	}


}
