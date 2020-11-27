package com.huaxia.opas.dao.retrieve.impl;

import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.retrieve.FicoBigResponseDao;

public class FicoBigResponseDaoImpl extends AbstractDAO implements FicoBigResponseDao {

	private static final long serialVersionUID = 1L;
	
	private static final String NAMESPACES = "FicoBigResponse.";

	@Override
	public int queryYCFQZJJS(Map<String, Object> map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "queryYCFQZJJS", map)));
	}

	@Override
	public int queryECFQZJJS(Map<String, Object> map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "queryECFQZJJS", map)));
	}

	@Override
	public int queryYCJCJJS(Map<String, Object> map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "queryYCJCJJS", map)));
	}

	@Override
	public int queryYCJCGJS(Map<String, Object> map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "queryYCJCGJS", map)));
	}

	@Override
	public int queryECJCJJS(Map<String, Object> map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "queryECJCJJS", map)));
	}

	@Override
	public int queryECJCGJS(Map<String, Object> map) throws CoreException {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "queryECJCGJS", map)));
	}
	
}
