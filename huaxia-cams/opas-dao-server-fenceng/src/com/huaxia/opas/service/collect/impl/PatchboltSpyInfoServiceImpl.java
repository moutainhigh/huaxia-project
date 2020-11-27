package com.huaxia.opas.service.collect.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.collect.PatchboltSpyInfoDao;
import com.huaxia.opas.dao.decision.FicoYdjBlazeDao;
import com.huaxia.opas.domain.collect.PatchboltSpyInfo;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.collect.PatchboltSpyInfoService;

public class PatchboltSpyInfoServiceImpl extends AbstractService implements PatchboltSpyInfoService , Serializable{
	
	private static final long serialVersionUID = 7696400194059513085L;
	
	@Resource(name = "patchboltSpyInfoDao")
	private PatchboltSpyInfoDao patchboltSpyInfoDao;
	
	@Resource(name = "ficoYdjBlazeDao")
	private FicoYdjBlazeDao ficoYdjBlazeDao;

	@Override
	public int addPatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException {
		return patchboltSpyInfoDao.addPatchboltSpyInfo(a);
	}

	@Override
	public int deletePatchboltSpyInfo(String a) throws CoreException {
		return patchboltSpyInfoDao.deletePatchboltSpyInfo(a);
	}

	@Override
	public int updatePatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException {
		return patchboltSpyInfoDao.updatePatchboltSpyInfo(a);
	}

	@Override
	public PatchboltSpyInfo selectPatchboltSpyInfo(String a)
			throws CoreException {
		return patchboltSpyInfoDao.selectPatchboltSpyInfo(a);
	}
	
	@Override
	public FicoYdjBlaze selectByPrimaryKey(String appId) throws CoreException {
		return ficoYdjBlazeDao.selectByPrimaryKey(appId);
	}

	@Override
	public FicoYdjBlaze selectByAppId(String appId) throws CoreException  {
		return ficoYdjBlazeDao.selectByAppId(appId);
	}

}
