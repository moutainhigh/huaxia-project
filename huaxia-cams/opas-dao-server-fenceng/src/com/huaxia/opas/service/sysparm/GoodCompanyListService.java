package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;
import com.huaxia.opas.domain.sysparm.GoodcompanyListHistory;

public interface GoodCompanyListService {
	
	int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(GoodcompanyList goodcompanylist) throws CoreException;

    int insertSelective(GoodcompanyList goodcompanylist) throws CoreException;

    GoodcompanyList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(GoodcompanyList goodcompanylist) throws CoreException;

    int updateByPrimaryKey(GoodcompanyList goodcompanylist) throws CoreException;
    
    //shihuan 2017-03-19  优质企业列表查询 
  	List<GoodcompanyList> queryCoodCompanyList(GoodcompanyList goodcompanylist, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-19  优质企业单列表查询条数 
  	int queryCoodCompanyCount(GoodcompanyList goodcompanylist) throws CoreException;
  	
  	//shihuan 2017-03-19  优质企业批量启动修改状态
  	int updateStartStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-19   优质企业批量停用修改状态
  	int updateStopStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-19  添加历史修改记录表
  	int insertHisSelective(GoodcompanyListHistory goodcompanylisthistory) throws CoreException;
  	
  	//shihuan 2017-03-19 历史优质企业列表查询 
  	List<GoodcompanyList> queryCoodCompanyHistory(String autoId, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-19 历史优质企业单列表查询条数 
  	int queryCoodCompanyHistoryCount(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-19  优质企业名单批导入文件
  	public int insertCoodCompUpload(List<GoodcompanyList> obj, BatchfileInfo batchfileInfo) throws CoreException;
  	
  	//shihuan 2017-04-24 校验学校名称不能重复 
    int queryByCompanyName(String companyName) throws CoreException;
}