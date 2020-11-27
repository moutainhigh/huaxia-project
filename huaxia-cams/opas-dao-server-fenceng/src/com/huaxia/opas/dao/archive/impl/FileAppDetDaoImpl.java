package com.huaxia.opas.dao.archive.impl;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.archive.FileAppDetDao;
import com.huaxia.opas.domain.archive.FileAppDet;

public class FileAppDetDaoImpl extends AbstractDAO implements FileAppDetDao {


	private static final long serialVersionUID = 2447021799565815630L;
	private static final String NAMESPACES = "FileAppDet.";

	@Override
	public int deleteByPrimaryKey(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", appId);
	}

	@Override
	public int insert(FileAppDet record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public FileAppDet selectByPrimaryKey(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", appId);
	}

	@Override
	public int updateByPrimaryKey(FileAppDet record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

}