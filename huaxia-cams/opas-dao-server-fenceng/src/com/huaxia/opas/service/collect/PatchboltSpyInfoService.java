package com.huaxia.opas.service.collect;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.collect.PatchboltSpyInfo;
import com.huaxia.opas.domain.input.FicoYdjBlaze;

public interface PatchboltSpyInfoService {

	
	public int addPatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException ;
	public int deletePatchboltSpyInfo(String a) throws CoreException ;
	public int updatePatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException ;
	public PatchboltSpyInfo selectPatchboltSpyInfo(String a) throws CoreException ;
	public FicoYdjBlaze selectByPrimaryKey(String appId) throws CoreException ;
	public FicoYdjBlaze selectByAppId(String appId) throws CoreException ;
}
