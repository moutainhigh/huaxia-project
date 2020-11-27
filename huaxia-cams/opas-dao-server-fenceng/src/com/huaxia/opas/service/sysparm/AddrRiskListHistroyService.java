package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.AddrRiskListHistory;

public interface AddrRiskListHistroyService {
	
    int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(AddrRiskListHistory addrrisklisthistory) throws CoreException;

    int insertSelective(AddrRiskListHistory addrrisklisthistory) throws CoreException;

    AddrRiskListHistory selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(AddrRiskListHistory addrrisklisthistory) throws CoreException;

    int updateByPrimaryKey(AddrRiskListHistory addrrisklisthistory) throws CoreException;
    
	//shihuan 2017-03-03 地址风险名单列表查询 
	List<AddrRiskListHistory> queryAddrRistListHistory(String autoId, int curNum, int pageNum);

	//shihuan 2017-03-03 地址风险名单列表查询条数 
	int queryAddrRistHistoryCount(String autoId);
	
	//shihuan 2017-03-10 地址风险名单批导入文件
	public int insertAddrHistoryList(List<AddrRiskListHistory> obj) throws CoreException;

}