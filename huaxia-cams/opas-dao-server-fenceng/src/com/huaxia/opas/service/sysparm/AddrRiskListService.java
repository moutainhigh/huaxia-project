package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;

public interface AddrRiskListService {
	
    int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(AddrRiskList addrrisklist) throws CoreException;

    int insertSelective(AddrRiskList addrrisklist) throws CoreException;

    AddrRiskList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(AddrRiskList addrrisklist) throws CoreException;

    int updateByPrimaryKey(AddrRiskList addrrisklist) throws CoreException;
    
	//shihuan 2017-03-03 地址风险名单列表查询 
	List<AddrRiskList> queryAddrRistList(AddrRiskList addrrisklist, int curNum, int pageNum);

	//shihuan 2017-03-03 地址风险名单列表查询条数 
	int queryAddrRistCount(AddrRiskList addrrisklist);
	
	//shihuan 2017-03-05  地址风险名单批量启动修改状态
	int updateStartStatus(String autoId) throws CoreException;
	
	//shihuan 2017-03-05  地址风险名单批量停用修改状态
	int updateStopStatus(String autoId) throws CoreException;
	
	//shihuan 2017-03-18 地址风险名单批导入文件
	public int insertAddrUpload(List<AddrRiskList> obj, BatchfileInfo batchfileInfo) throws CoreException;
}