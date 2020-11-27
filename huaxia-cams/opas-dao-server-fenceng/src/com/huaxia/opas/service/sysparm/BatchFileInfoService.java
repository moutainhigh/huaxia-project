package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;

public interface BatchFileInfoService {
	
	//名单上传记录
    int insert(BatchfileInfo batchfileinfo);

    int insertSelective(BatchfileInfo batchfileinfo);
    
    //shihuan 2017-04-07 
  	List<BatchfileInfo> selectAll(BatchfileInfo batchfileinfo, int curNum, int pageNum);

  	//shihuan 2017-04-07 
  	int selectAllCount(BatchfileInfo batchfileinfo);
  	
  	
  	//风险名单上传记录
  	int insertRist(BatchfileInfo batchfileinfo);

    int insertRistSelective(BatchfileInfo batchfileinfo);
    
    //shihuan 2017-04-07 
  	List<BatchfileInfo> selectRistAll(BatchfileInfo batchfileinfo, int curNum, int pageNum);

  	//shihuan 2017-04-07
  	int selectRistAllCount(BatchfileInfo batchfileinfo);
}