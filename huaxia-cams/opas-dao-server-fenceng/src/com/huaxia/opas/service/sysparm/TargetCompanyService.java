package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;

public interface TargetCompanyService {
	
	int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(TargetcompanyList targetcompanylist) throws CoreException;

    int insertSelective(TargetcompanyList targetcompanylist) throws CoreException;

    TargetcompanyList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(TargetcompanyList targetcompanylist) throws CoreException;

    int updateByPrimaryKey(TargetcompanyList targetcompanylist) throws CoreException;
    
    //shihuan 2017-03-21  目标企业列表查询 
  	List<TargetcompanyList> queryTargetCompany(TargetcompanyList targetcompanylist, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-21  目标企业单列表查询条数 
  	int queryTargetCompanyCount(TargetcompanyList targetcompanylist) throws CoreException;
  	
  	//shihuan 2017-03-21  目标企业批量启动修改状态
  	int updateStartStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-21   目标企业批量停用修改状态
  	int updateStopStatus(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-21  添加历史修改记录表
  	int insertHisSelective(TargetcompanyList targetcompanylist) throws CoreException;
  	
  	//shihuan 2017-03-21 历史目标企业列表查询 
  	List<TargetcompanyList> queryTargetCompanyHistory(String autoId, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-21 历史目标企业单列表查询条数 
  	int queryTargetCompHistoryCount(String autoId) throws CoreException;
  	
  	//shihuan 2017-03-21  目标企业名单批导入文件
  	public int insertTargetComUpload(List<TargetcompanyList> obj, BatchfileInfo batchfileInfo) throws CoreException;
  	
	//shihuan 2017-04-25  校验公司名称不能重复 
  	int queryCompanyName(String companyName) throws CoreException;
}