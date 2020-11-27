package com.huaxia.opas.dao.allot.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotNumberDao;
import com.huaxia.opas.domain.allot.AllotNumber;

public class AllotNumberDaoImpl extends AbstractDAO implements AllotNumberDao {

	private static final long serialVersionUID = -423065388810721617L;
	private static final String NAMESPACES = "AllotNumber.";

	@Override
	public int insertAllotNumber(AllotNumber allotNumber)  throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertAllotNumberSelective", allotNumber);
	}

	@Override
	public int updateAllotNumber(AllotNumber allotNumber) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateAllotNumberSelective", allotNumber);
	}

	@Override
	public AllotNumber selectAllotNumber(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryAllotNumber", map);
	}

	@Override
	public List<AllotNumber> selectAllotNumberList(Map map) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryAllotNumber", map);
	}
	
	@Override
	public String countAllotNumber(Map map) throws CoreException {
		 return getSqlMap().queryForObject(NAMESPACES + "countAllotNumber",map);
	}
	
	@Override
	public String queryCreditRuleAllotNumber(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryCreditRuleAllotNumber",map);
	}
	
}
