package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.CompanyInfoList;

public interface CompanyInfoService {

	
	int deleteByPrimaryKey(String autoId) throws CoreException;

    int insert(CompanyInfoList companyInfolist) throws CoreException;

    int insertSelective(CompanyInfoList companyInfolist) throws CoreException;

    CompanyInfoList selectByPrimaryKey(String autoId) throws CoreException;

    int updateByPrimaryKeySelective(CompanyInfoList companyInfolist) throws CoreException;

    int updateByPrimaryKey(CompanyInfoList companyInfolist) throws CoreException;
    
  	//chenmeng 2019-04-12  3311企业名单库批量启动修改状态
  	//int updateStartStatus(String autoId) throws CoreException;
  	
  	//chenmeng 2019-04-12  3311企业名单库批量停用修改状态
  	//int updateStopStatus(String autoId) throws CoreException;
  	
  	//chenmeng 2019-04-12  添加历史修改记录表
  	int insertHisSelective(CompanyInfoList companyInfolisthistory) throws CoreException;
  	
  	//chenmeng 2019-04-12  3311企业名单库批导入文件
  	public int insertCompanyInfoUpload(List<CompanyInfoList> obj, BatchfileInfo batchfileInfo) throws CoreException;
  	
	//chenmeng 2019-04-12   校验公司名称不能重复 
  	int queryCompanyName(String companyName) throws CoreException;
  	
  	//chenmeng 2019-04-12 历史3311企业名单列表查询条数 
	int queryCompanyInfoHistoryCount(String autoId) throws CoreException;

	//chenmeng 2019-04-12 历史3311企业名单列表查询
	List<CompanyInfoList> queryCompanyInfoHistory(String autoId, int curNum, int pageNum) throws CoreException;

	//chenmeng 2019-04-12  3311企业名单列表查询条数 
	int queryCompanyInfoCount(CompanyInfoList companyInfoList) throws CoreException;

	//chenmeng 2019-04-12  3311企业名单列表查询 
	List<CompanyInfoList> queryCompanyInfo(CompanyInfoList companyInfoList, int curNum, int pageNum) throws CoreException;
	
	

}
