package com.huaxia.opas.dao.allot.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotTimeDao;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotTime;

public class AllotTimeDaoImpl extends AbstractDAO implements AllotTimeDao {

	private static final long serialVersionUID = -7113038975837097918L;
	private static final String NAMESPACES = "AllotTime.";

	@Override
	public int insertAllotTime(AllotTime allotTime) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", allotTime);
	}
	
	@Override
	public int updateAllotTime(AllotTime allotTime) throws CoreException {
		return getSqlMap().update(NAMESPACES + "update", allotTime);
	}
	
	@Override
	public int deleteAllotTime(String id) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "delete", id);
	}
	
	@Override
	public int countAllotTime(Map map) throws CoreException {
		return  getSqlMap().queryForObject(NAMESPACES + "count",map);
	}
	
	@Override
	public List<AllotTime> selectAllotTime(Map map,int currentPage, int pageSize) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "select", map,currentPage , pageSize);
	}

	@Override
	public AllotTime selectAllotTime(Map map) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "select", map);
	}
}
