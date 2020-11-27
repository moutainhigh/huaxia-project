package com.huaxia.opas.dao.allot.impl;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotBzkResultDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;

@SuppressWarnings("serial")
public class AllotBzkResultDaoImpl extends AbstractDAO implements AllotBzkResultDao {

	private static final String NAMESPACES = "OpaBzkSysResultInfoMapper.";
	@Override
	public int insertBzkResultInfo(OpaBzkSysResultInfo info) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertBzkResultInfo", info);
	}
	
	@Override
	public int selectCount(String appId) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCount",appId);
	}
	
	@Override
	public String selectResultByAppId(String appId) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectResultByAppId",appId);
	}
	
	@Override
	public int updateBzkResultInfo(OpaBzkSysResultInfo info) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateBzkResultInfo", info);
	}
}
