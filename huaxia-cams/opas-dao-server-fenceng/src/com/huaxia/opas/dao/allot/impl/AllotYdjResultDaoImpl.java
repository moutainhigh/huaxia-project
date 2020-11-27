package com.huaxia.opas.dao.allot.impl;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotYdjResultDao;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;

@SuppressWarnings("serial")
public class AllotYdjResultDaoImpl extends AbstractDAO implements AllotYdjResultDao {

	private static final String NAMESPACES = "YdjSysresultInfo.";
	@Override
	public int insertYdjResultInfo(YdjSysresultInfo info) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertInfo", info);
	}
	
	@Override
	public int selectCount(String appId) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectCount",appId);
	}
	
	@Override
	public int updateYdjResultInfo(YdjSysresultInfo info) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateYdjResultInfo", info);
	}
}
