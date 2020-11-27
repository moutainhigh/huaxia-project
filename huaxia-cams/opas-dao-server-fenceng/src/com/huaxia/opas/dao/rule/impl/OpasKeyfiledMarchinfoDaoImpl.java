package com.huaxia.opas.dao.rule.impl;

import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasKeyfiledMarchinfoDao;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;

public class OpasKeyfiledMarchinfoDaoImpl extends AbstractDAO implements OpasKeyfiledMarchinfoDao {

	private static final long serialVersionUID = 2751582418545513921L;

	private static final String NAMESPACES = "KeyfiledMarchinfo.";

	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(KeyfiledMarchinfo record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(KeyfiledMarchinfo record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public KeyfiledMarchinfo selectByPrimaryKey(String autoId) {
		return (KeyfiledMarchinfo) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(KeyfiledMarchinfo record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(KeyfiledMarchinfo record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(KeyfiledMarchinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public KeyfiledMarchinfo selectByAppId(Map<String, String> map) {
		return (KeyfiledMarchinfo) (getSqlMap().queryForObject(NAMESPACES + "selectByAppId", map));
	}

}