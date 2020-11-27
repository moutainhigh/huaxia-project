package com.huaxia.opas.dao.allot.impl;


import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotBatchFailDao;

public class AllotBatchFailDaoImpl extends AbstractDAO implements AllotBatchFailDao {

	private static final long serialVersionUID = -8091519228146342554L;
	private static final String NAMESPACES = "AllotBatch.";

	@Override
	public int insertFailApply(String appId) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertFailApply", appId);
	}
	
	@Override
	public int insertFailApply(Map map) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertFailApplySelective", map);
	}
	
	@Override
	public int updateFailApply(Map map) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateFailApply", map);
	}
	
	@Override
	public int selectCount(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCount",map);
	}
}
