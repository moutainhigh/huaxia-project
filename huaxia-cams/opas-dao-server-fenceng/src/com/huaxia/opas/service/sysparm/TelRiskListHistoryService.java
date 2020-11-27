package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.riskcheck.TelRiskListHistory;

public interface TelRiskListHistoryService {
	
    int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(TelRiskListHistory telRiskListHistory) throws CoreException;

    int insertSelective(TelRiskListHistory telRiskListHistory) throws CoreException;

    TelRiskList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(TelRiskListHistory telRiskListHistory) throws CoreException;

    int updateByPrimaryKey(TelRiskListHistory telRiskListHistory) throws CoreException;
    
    //shihuan 2017-03-13 地址风险名单列表查询 
  	List<TelRiskListHistory> queryTelRistHisList(String autoId, int curNum, int pageNum);

  	//shihuan 2017-03-13 地址风险名单列表查询条数 
  	int queryTelRistHisCount(String autoId);
}