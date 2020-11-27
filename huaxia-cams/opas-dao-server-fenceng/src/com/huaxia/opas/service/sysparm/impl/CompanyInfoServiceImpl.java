package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.CompanyInfoDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.CompanyInfoList;
import com.huaxia.opas.service.sysparm.CompanyInfoService;

public class CompanyInfoServiceImpl extends AbstractDAO implements CompanyInfoService, Serializable{

	private static final long serialVersionUID = 9161494730976571690L;

	@Resource(name = "companyInfoDao")
	private CompanyInfoDao companyInfoDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public CompanyInfoDao getCompanyInfoDao() {
		return companyInfoDao;
	}

	public void setCompanyInfoDao(CompanyInfoDao companyInfoDao) {
		this.companyInfoDao = companyInfoDao;
	}

	@Override
	public int insert(CompanyInfoList companyInfolist) throws CoreException {
		return getCompanyInfoDao().insert(companyInfolist);
	}


	@Override
	public int insertSelective(CompanyInfoList companyInfolist) throws CoreException {
		return getCompanyInfoDao().insertSelective(companyInfolist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getCompanyInfoDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(CompanyInfoList companyInfolist) throws CoreException {
		return getCompanyInfoDao().updateByPrimaryKey(companyInfolist);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyInfoList companyInfolist) throws CoreException {
		return getCompanyInfoDao().updateByPrimaryKeySelective(companyInfolist);
	}

	@Override
	public CompanyInfoList selectByPrimaryKey(String autoId) throws CoreException {
		return getCompanyInfoDao().selectByPrimaryKey(autoId);
	}
	
	//chenmeng 2019-04-12 3311企业名单库列表查询 
	@Override
	public List<CompanyInfoList> queryCompanyInfo(CompanyInfoList companyInfolist, int curNum, int pageNum) throws CoreException {
		return getCompanyInfoDao().queryCompanyInfo(companyInfolist, curNum, pageNum);
	}

	//chenmeng 2019-04-12 3311企业名单库列表查询 条数
	@Override
	public int queryCompanyInfoCount(CompanyInfoList companyInfolist) throws CoreException {
		return getCompanyInfoDao().queryCompanyInfoCount(companyInfolist);
	}
	
	//chenmeng 2019-04-12 3311企业名单库批量启动停用修改状态
	/*@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getCompanyInfoDao().updateStartStatus(autoId);
	}*/
	
	//chenmeng 2019-04-12 3311企业名单库批量启动停用修改状态
	/*@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getCompanyInfoDao().updateStopStatus(autoId);
	}*/
	
	//chenmeng 2019-04-12  添加历史修改记录表
	@Override
	public int insertHisSelective(CompanyInfoList companyInfolist) throws CoreException {
		return getCompanyInfoDao().insertHisSelective(companyInfolist);
	}
	
	//chenmeng 2019-04-12  历史3311企业名单库列表查询 
	@Override
	public List<CompanyInfoList> queryCompanyInfoHistory(String autoId, int curNum, int pageNum) throws CoreException {
		return getCompanyInfoDao().queryCompanyInfoHistory(autoId, curNum, pageNum);
	}

	//chenmeng 2019-04-12   历史3311企业名单库列表查询 条数
	@Override
	public int queryCompanyInfoHistoryCount(String autoId) throws CoreException {
		return getCompanyInfoDao().queryCompanyInfoHistoryCount(autoId);
	}
	//chenmeng 2019-04-12 3311企业名单库名单批导入文件
	@Override
	public int insertCompanyInfoUpload(List<CompanyInfoList> obj, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = companyInfoDao.insertCompanyInfoUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
	//chenmeng 2019-04-12  历史3311企业名单库列表查询 条数
	@Override
	public int queryCompanyName(String companyName) throws CoreException {
		return getCompanyInfoDao().queryCompanyName(companyName);
	}
}
