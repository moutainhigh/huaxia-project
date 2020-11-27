package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.ConfRosterLibraryDao;
import com.huaxia.opas.domain.sysparm.ConfRosterLibrary;

public class ConfRosterLibraryDaoImpl extends AbstractDAO implements ConfRosterLibraryDao {

	private static final long serialVersionUID = -6602944725836702754L;
	
	private static final String NAMESPACES = "ConfRosterLibrary.";

	@Override
	public int insertConfRosterLibrary(ConfRosterLibrary confRosterLibrary) {
		return getSqlMap().insert(NAMESPACES + "insertConfRosterLibrary",confRosterLibrary);
	}

	@Override
	public int queryConfRosterLibraryCount(ConfRosterLibrary confRosterLibrary) {
		return getSqlMap().queryForObject(NAMESPACES + "queryConfRosterLibraryCount",confRosterLibrary);
	}

	@Override
	public List<ConfRosterLibrary> queryConfRosterLibraryList(ConfRosterLibrary confRosterLibrary, int curNum, int pageNum) {
		List<ConfRosterLibrary> list = new ArrayList<ConfRosterLibrary>();
		list = getSqlMap().queryForList(NAMESPACES + "queryConfRosterLibraryForList", confRosterLibrary,curNum,pageNum);
		return list;
	}

	@Override
	public int updateConfRosterLibrary(ConfRosterLibrary confRosterLibrary) {
		return getSqlMap().update(NAMESPACES + "updateConfRosterLibrary", confRosterLibrary); 
	}

	@Override
	public int deleteConfRosterLibrary(ConfRosterLibrary confRosterLibrary) {
		List<String> ids = confRosterLibrary.getIds();
		return getSqlMap().delete(NAMESPACES + "deleteConfRosterLibrary",ids);
	}

	@Override
	public int insertConfRosterLibrary(List<ConfRosterLibrary> rosterlist) {
		return getSqlMap().insert(NAMESPACES + "insertConfRosterLibraryList", rosterlist);
	}

	@Override
	public ConfRosterLibrary queryOnly(ConfRosterLibrary confRosterLibrary) {
		return getSqlMap().queryForObject(NAMESPACES + "queryConfRosterLibraryOnly",confRosterLibrary);
	}

	@Override
	public int deleteAll() {
		return getSqlMap().delete(NAMESPACES + "deleteAll");
	}

}
