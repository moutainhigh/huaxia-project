package com.huaxia.opas.service.search;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.search.BranchLevelParam;

public interface BranchLevelParamService {

	public int insertBranchLevelParam(BranchLevelParam branchLevelParam) throws CoreException;
	public int insertBranchLevelParamSelective(BranchLevelParam branchLevelParam) throws CoreException;
	public int deleteBranchLevelParamByProvId(Map map) throws CoreException;
	public int updateBranchLevelParam(BranchLevelParam branchLevelParam) throws CoreException;
	public int updateBranchLevelParamSelective(BranchLevelParam branchLevelParam) throws CoreException;
	public BranchLevelParam queryBranchLevelParamByProvId(String provId) throws CoreException;
	public List<BranchLevelParam> queryBranchLevelParamList(BranchLevelParam branchLevelParam)throws CoreException;
	public int queryBranchCountByBranchAppOrgCode(Map<String, Object> map) throws CoreException;
	public String queryOneBrankCityNameByCode(String towLevelCityCode) throws CoreException;
}
