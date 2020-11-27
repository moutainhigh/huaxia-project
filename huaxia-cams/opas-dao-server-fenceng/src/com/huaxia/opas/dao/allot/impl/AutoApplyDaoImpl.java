package com.huaxia.opas.dao.allot.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AutoApplyDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AutoApply;

public class AutoApplyDaoImpl extends AbstractDAO implements AutoApplyDao,Serializable {

	private static final long serialVersionUID = 8219858450982325987L;

	private static final String NAMESPACES="AutoApply.";
	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "selectByPrimaryKey", autoId);
	}

	@Override
	public int insert(AutoApply record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(AutoApply record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public AutoApply selectByPrimaryKey(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId);
	}

	@Override
	public int updateByPrimaryKeySelective(AutoApply record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(AutoApply record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int selectBzkAuto(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectBzkAuto", map);
	}

	@Override
	public int selectYdjAuto(Map map) {
		return getSqlMap().queryForObject(NAMESPACES + "selectYdjAuto", map);
	}

	@Override
	public List<AllotApply> selectBzkAutoList(Map map,int page, int rows) {
		return getSqlMap().queryForList(NAMESPACES + "selectBzkAutoList", map,page,rows);
	}

	@Override
	public List<AllotApply> selectYdjAutoList(Map map,int page, int rows) {
		return getSqlMap().queryForList(NAMESPACES + "selectYdjAutoList", map,page,rows);
	}
	
}
