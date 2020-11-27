package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.CompanyRiskHistory;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;

public interface CompanyRiskService {
	//页数
	public int queryCompanyRiskCount(CompanyRisk companyRisk) throws CoreException;
	//list
	public List<CompanyRisk> queryCompanyRiskList(CompanyRisk companyRisk, int curNum, int pageNum) throws CoreException;

	public int insertCompanyRisk(CompanyRisk companyRisk) throws CoreException;

	public int updateCompanyRisk(CompanyRisk companyRisk) throws CoreException;

	public int deleteCompanyRisk(String autoId) throws CoreException;
	
	//shihuan 2017-03-05  公司地址风险名单批量启动修改状态
	int updateStartStatus(String autoId) throws CoreException;
	
	//shihuan 2017-03-05  公司地址风险名单批量停用修改状态
	int updateStopStatus(String autoId) throws CoreException;

	//shihuan 2017-03-14 公司地址风险列表查询 
  	List<CompanyRisk> queryCompanyRistListHistory(String autoId, int curNum, int pageNum) throws CoreException;

  	//shihuan 2017-03-14  公司地址风险列表查询条数 
  	int queryCompanyRistHistoryCount(String autoId) throws CoreException;
  	
	public CompanyRisk selectByPrimaryKey(String autoId) throws CoreException;
	
	//添加历史记录
	public int insertCompanyRiskHis(CompanyRiskHistory companyriskhistory) throws CoreException;
	
	//shihuan 2017-03-18 单位风险名单批导入文件
	public int insertCompanyUpload(List<CompanyRisk> obj, BatchfileInfo batchfileInfo) throws CoreException;
	
  	//新增修改时校验单位名称是否添加重复  shihuan  2017-04-18
  	public int queryByComName(String companyName) throws CoreException;
}
