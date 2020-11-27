package com.huaxia.opas.dao.sysparm;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.BatchfileLowRiskInfo;

public interface BatchFileLowRiskInfoDao {
	//名单上传记录
    int insert(BatchfileLowRiskInfo batchfileLowRiskInfo);

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
  	
    //获取创建人
    String selectUser();
    
    //写入总行数和异常信息
    int updateTotalCount(Map<String, Object> args);
    
    //更新状态
    int updateStatus(String status);

}