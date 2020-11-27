package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.CompanyRiskDao;
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.CompanyRiskHistory;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.sysparm.CompanyRiskService;

public class CompanyRiskServiceImpl extends AbstractDAO implements CompanyRiskService , Serializable{
	
	private static final long serialVersionUID = 5470178573528036415L;
	
	@Resource(name = "companyRiskDao")
	private CompanyRiskDao companyRiskDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public CompanyRiskDao getCompanyRiskDao() {
		return companyRiskDao;
	}

	public void setCompanyRiskDao(CompanyRiskDao companyRiskDao) {
		this.companyRiskDao = companyRiskDao;
	}

	//添加
	public int insertCompanyRisk(CompanyRisk companyRisk) throws CoreException {
		return getCompanyRiskDao().insertCompanyRisk(companyRisk);
	}
	
	//查询的页数
	@Override
	public int queryCompanyRiskCount(CompanyRisk companyRisk) throws CoreException {
		return getCompanyRiskDao().queryCompanyRiskCount(companyRisk);
	}
	
	//查询
	@Override
	public List<CompanyRisk> queryCompanyRiskList(CompanyRisk companyRisk, int curNum, int pageNum) throws CoreException  {
		return getCompanyRiskDao().queryCompanyRiskList(companyRisk, curNum, pageNum);
	}
	//修改
	@Override
	public int updateCompanyRisk(CompanyRisk companyRisk) throws CoreException {
		return getCompanyRiskDao().updateCompanyRisk(companyRisk);
	}
	//删除
	@Override
	public int deleteCompanyRisk(String autoId) throws CoreException {
		
		return getCompanyRiskDao().deleteCompanyRisk(autoId);
	}

	//shihuan 2017-03-05  地址风险名单批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getCompanyRiskDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-05  地址风险名单批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getCompanyRiskDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-15 历史公司地址列表查询 
	@Override
	public List<CompanyRisk> queryCompanyRistListHistory(String autoId, int curNum, int pageNum) throws CoreException {
		return getCompanyRiskDao().queryCompanyRistListHistory(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-15 历史公司地址列表查询  条数
	@Override
	public int queryCompanyRistHistoryCount(String autoId) throws CoreException {
		return getCompanyRiskDao().queryCompanyRistHistoryCount(autoId);
	}
	
	@Override
	public CompanyRisk selectByPrimaryKey(String autoId) throws CoreException {
		return getCompanyRiskDao().selectByPrimaryKey(autoId);
	}
	
	//添加
	public int insertCompanyRiskHis(CompanyRiskHistory companyriskhistory) throws CoreException {
		return getCompanyRiskDao().insertCompanyRiskHis(companyriskhistory);
	}
	
	//shihuan 2017-03-18 单位风险名单批导入文件
	@Override
	public int insertCompanyUpload(List<CompanyRisk> obj,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = companyRiskDao.insertCompanyUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insertRist(batchfileInfo);
		return inputCounts;
	}
	
	//新增修改时校验单位名称是否添加重复  shihuan  2017-04-18
	@Override
	public int queryByComName(String companyName) throws CoreException {
		return getCompanyRiskDao().queryByComName(companyName);
	}
}

