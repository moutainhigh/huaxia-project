package com.huaxia.opas.dao.allot.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotSwitchDao;
import com.huaxia.opas.domain.allot.AllotSwitch;

public class AllotSwitchDaoImpl extends AbstractDAO implements AllotSwitchDao {

	private static final long serialVersionUID = 1947124527732677330L;
	private static final String NAMESPACES = "AllotSwitch.";

	@Override
	public int countAllotSwitch(String switchCode) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "countAllotSwitch",switchCode);
	}
	
	@Override
	public int insertAllotSwitch(AllotSwitch allotSwitch) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAllotSwitch", allotSwitch);
	}

	@Override
	public int updateAllotSwitch(AllotSwitch allotSwitch) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAllotSwitch", allotSwitch);
	}
	
	@Override
	public AllotSwitch queryAllotSwitchByCode(String switchCode) throws CoreException {
		return (AllotSwitch)(getSqlMap().queryForObject(NAMESPACES + "queryAllotSwitchByCode", switchCode));
	}

	@Override
	public int selectHoliday() throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "selectHoliday");
	}

}
