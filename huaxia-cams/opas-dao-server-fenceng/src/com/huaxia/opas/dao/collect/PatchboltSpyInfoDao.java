package com.huaxia.opas.dao.collect;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.collect.PatchboltSpyInfo;

public interface PatchboltSpyInfoDao {

	public int addPatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException;

	public int deletePatchboltSpyInfo(String a) throws CoreException;

	public int updatePatchboltSpyInfo(PatchboltSpyInfo a) throws CoreException;

	public PatchboltSpyInfo selectPatchboltSpyInfo(String a) throws CoreException;

}
