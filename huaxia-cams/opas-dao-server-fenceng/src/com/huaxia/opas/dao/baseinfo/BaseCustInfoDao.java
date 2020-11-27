package com.huaxia.opas.dao.baseinfo;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.baseinfo.BaseCustInfo;

public interface BaseCustInfoDao {
	
	public int insertCustInfo(BaseCustInfo baseCustInfo) throws CoreException;

	public Map<String, Object> queryFromBizInpApplC1(String appId) throws CoreException;

	//public int queryFromTeamList(String c4Apbatch);

	//public int queryFromTeamDealList(String c4Apbatch);

	public Map<String, Object> queryPoliceStatus(String appId) throws CoreException;

	public String selectCustInfo(String appId) throws CoreException;

	public int updateCustInfo(BaseCustInfo baseCustInfo) throws CoreException;

	public Map<String, String> queryBizInpApplC1(String idNbr);

	public List<Map<String, String>> queryBaseCustByIdNbr(Map<String, String> matchingCard);

	public void updateBaseCustInfo(BaseCustInfo baseCustInfo);

	//public int queryIsHaveCard(String appId);
	
}
