package com.huaxia.opas.dao.sysparm.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.domain.sysparm.TelSource;
import com.huaxia.opas.dao.sysparm.TelSourceDao;

/**
 * 电话来源码DAO层实现类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelSourceDaoImpl extends AbstractDAO implements TelSourceDao{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4863123787314935168L;
	/**
	 * 
	 */
	private static final String NAMESPACES = "TelSource.";

	@Override
	public int saveTelSourceStart(TelSource telSource) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelSource1", telSource);
	}
	
	@Override
	public int saveTelSourceEnd(TelSource telSource) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertTelSource2", telSource);
	}

	@Override
	public int updateTelSource(TelSource telSource) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateTelSource", telSource);
	}

	@Override
	public int deleteTelSource(String autoId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteTelSourceById", autoId);
	}
	
	@Override
	public int queryTelSourceCount(TelSource telSource) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelSourceCount", telSource);
	}

	@Override
	public List<TelSource> queryTelSource(TelSource telSource,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelSource1", telSource, curNum, pageNum);
	}

	@Override
	public List<TelSource> queryTelSource(Map<String,Object> params,int curNum,int pageNum) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryTelSource2", params, curNum, pageNum);
	}
	
	@Override
	public TelSource queryTelSource(TelSource telSource) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelSource3", telSource);
	}

	@Override
	public String queryTelSourceStatus(String autoId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryTelSourceStatus", autoId);
	}

	@Override
	public int updateTelSourceWithoutStatus(TelSource telSource) {
		return getSqlMap().update(NAMESPACES + "updateTelSourceWithoutStatus", telSource);
	}

}
