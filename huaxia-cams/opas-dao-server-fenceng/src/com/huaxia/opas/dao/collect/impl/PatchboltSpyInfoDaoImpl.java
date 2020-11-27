package com.huaxia.opas.dao.collect.impl;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.collect.PatchboltSpyInfoDao;
import com.huaxia.opas.domain.collect.PatchboltSpyInfo;

public class PatchboltSpyInfoDaoImpl extends AbstractDAO implements PatchboltSpyInfoDao {
	
	private static final long serialVersionUID = -8345815009703094853L;
	
	private static final String NAMESPACES = "PatchboltSpyInfo.";

	@Override
	public int addPatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insert", a);
	}

	@Override
	public int deletePatchboltSpyInfo(String a) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", a);
	}

	@Override
	public int updatePatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateByAppIdKey", a);
	}

	@Override
	public PatchboltSpyInfo selectPatchboltSpyInfo(String a) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", a);
	}

}
