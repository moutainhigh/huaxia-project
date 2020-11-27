package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasrevieentrycompareinfoDao;
import com.huaxia.opas.domain.rule.Opasrevieentrycompareinfo;

public class OpasrevieentrycompareinfoDaoImpl extends AbstractDAO implements OpasrevieentrycompareinfoDao,Serializable {

	@Override
	public int deleteByPrimaryKey(String appId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Opasrevieentrycompareinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Opasrevieentrycompareinfo> selectByExample(Opasrevieentrycompareinfo example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Opasrevieentrycompareinfo selectByPrimaryKey(String appId) {
		// TODO Auto-generated method stub
		return getSqlMap().queryForObject("Opasrevieentrycompareinfo.selectByPrimaryKey", appId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> selectSignFullInfo(String appId) {
		List<Object> queryForList = getSqlMap().queryForList("Opasrevieentrycompareinfo.selectSignFullInfo", appId);
		if(!queryForList.isEmpty()){
			return (Map<String, String>)queryForList.get(0);
		}else{
			Map<String, String> rstMap = new HashMap<String,String>();
			rstMap.put("signFull", "");
			rstMap.put("applyInputfull", "");
			return rstMap;
		}
	}
}
