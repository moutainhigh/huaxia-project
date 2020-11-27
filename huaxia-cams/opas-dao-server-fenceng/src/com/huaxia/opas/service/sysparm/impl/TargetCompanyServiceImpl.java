package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.TargetCompanyDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.service.sysparm.TargetCompanyService;

public class TargetCompanyServiceImpl extends AbstractDAO implements TargetCompanyService, Serializable {

	private static final long serialVersionUID = -8031110907080998923L;
	
	@Resource(name = "targetCompanyDao")
	private TargetCompanyDao targetCompanyDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public TargetCompanyDao getTargetCompanyDao() {
		return targetCompanyDao;
	}

	public void setTargetCompanyDao(TargetCompanyDao targetCompanyDao) {
		this.targetCompanyDao = targetCompanyDao;
	}

	@Override
	public int insert(TargetcompanyList targetcompanylist) throws CoreException {
		return getTargetCompanyDao().insert(targetcompanylist);
	}


	@Override
	public int insertSelective(TargetcompanyList targetcompanylist) throws CoreException {
		return getTargetCompanyDao().insertSelective(targetcompanylist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getTargetCompanyDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(TargetcompanyList targetcompanylist) throws CoreException {
		return getTargetCompanyDao().updateByPrimaryKey(targetcompanylist);
	}

	@Override
	public int updateByPrimaryKeySelective(TargetcompanyList targetcompanylist) throws CoreException {
		return getTargetCompanyDao().updateByPrimaryKeySelective(targetcompanylist);
	}

	@Override
	public TargetcompanyList selectByPrimaryKey(String autoId) throws CoreException {
		return getTargetCompanyDao().selectByPrimaryKey(autoId);
	}
	
	//shihuan 2017-03-21 目标企业列表查询 
	@Override
	public List<TargetcompanyList> queryTargetCompany(TargetcompanyList targetcompanylist, int curNum, int pageNum) throws CoreException {
		return getTargetCompanyDao().queryTargetCompany(targetcompanylist, curNum, pageNum);
	}

	//shihuan 2017-03-21 目标企业列表查询 条数
	@Override
	public int queryTargetCompanyCount(TargetcompanyList targetcompanylist) throws CoreException {
		return getTargetCompanyDao().queryTargetCompanyCount(targetcompanylist);
	}
	
	//shihuan 2017-03-21  目标企业批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getTargetCompanyDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-21  目标企业批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getTargetCompanyDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-21  添加历史修改记录表
	@Override
	public int insertHisSelective(TargetcompanyList targetcompanylist) throws CoreException {
		return getTargetCompanyDao().insertHisSelective(targetcompanylist);
	}
	
	//shihuan 2017-03-21 历史目标企业列表查询 
	@Override
	public List<TargetcompanyList> queryTargetCompanyHistory(String autoId, int curNum, int pageNum) throws CoreException {
		return getTargetCompanyDao().queryTargetCompanyHistory(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-21 历史目标企业列表查询 条数
	@Override
	public int queryTargetCompHistoryCount(String autoId) throws CoreException {
		return getTargetCompanyDao().queryTargetCompHistoryCount(autoId);
	}
	//shihuan 2017-03-21 目标企业名单批导入文件
	@Override
	public int insertTargetComUpload(List<TargetcompanyList> obj, BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = targetCompanyDao.insertTargetComUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
	//shihuan 2017-03-21 历史目标企业列表查询 条数
	@Override
	public int queryCompanyName(String companyName) throws CoreException {
		return getTargetCompanyDao().queryCompanyName(companyName);
	}
}