package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.dao.sysparm.GoodCompanyListDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;
import com.huaxia.opas.domain.sysparm.GoodcompanyListHistory;
import com.huaxia.opas.service.sysparm.GoodCompanyListService;

public class GoodCompanyListServiceImpl extends AbstractDAO implements GoodCompanyListService, Serializable{

	private static final long serialVersionUID = -8031110907080998923L;
	
	@Resource(name = "goodCompanyListDao")
	private GoodCompanyListDao goodCompanyListDao;
	
	@Resource(name="batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	public GoodCompanyListDao getGoodCompanyListDao() {
		return goodCompanyListDao;
	}

	public void setGoodCompanyListDao(GoodCompanyListDao goodCompanyListDao) {
		this.goodCompanyListDao = goodCompanyListDao;
	}

	@Override
	public int insert(GoodcompanyList goodcompanylist) throws CoreException {
		return getGoodCompanyListDao().insert(goodcompanylist);
	}


	@Override
	public int insertSelective(GoodcompanyList goodcompanylist) throws CoreException {
		return getGoodCompanyListDao().insertSelective(goodcompanylist);
	}

	@Override
	public int deleteByPrimaryKey(String autoId) throws CoreException {
		return getGoodCompanyListDao().deleteByPrimaryKey(autoId);
	}

	@Override
	public int updateByPrimaryKey(GoodcompanyList goodcompanylist) throws CoreException {
		return getGoodCompanyListDao().updateByPrimaryKey(goodcompanylist);
	}

	@Override
	public int updateByPrimaryKeySelective(GoodcompanyList goodcompanylist) throws CoreException {
		return getGoodCompanyListDao().updateByPrimaryKeySelective(goodcompanylist);
	}

	@Override
	public GoodcompanyList selectByPrimaryKey(String autoId) throws CoreException {
		return getGoodCompanyListDao().selectByPrimaryKey(autoId);
	}
	
	//shihuan 2017-03-19 优质企业列表查询 
	@Override
	public List<GoodcompanyList> queryCoodCompanyList(GoodcompanyList goodcompanylist, int curNum, int pageNum) throws CoreException {
		return getGoodCompanyListDao().queryCoodCompanyList(goodcompanylist, curNum, pageNum);
	}

	//shihuan 2017-03-19 优质企业列表查询 条数
	@Override
	public int queryCoodCompanyCount(GoodcompanyList goodcompanylist) throws CoreException {
		return getGoodCompanyListDao().queryCoodCompanyCount(goodcompanylist);
	}
	
	//shihuan 2017-03-19  优质企业批量启动停用修改状态
	@Override
	public int updateStartStatus(String autoId) throws CoreException {
		return getGoodCompanyListDao().updateStartStatus(autoId);
	}
	
	//shihuan 2017-03-19  优质企业批量启动停用修改状态
	@Override
	public int updateStopStatus(String autoId) throws CoreException {
		return getGoodCompanyListDao().updateStopStatus(autoId);
	}
	
	//shihuan 2017-03-19  添加历史修改记录表
	@Override
	public int insertHisSelective(GoodcompanyListHistory goodcompanylisthistory) throws CoreException {
		return getGoodCompanyListDao().insertHisSelective(goodcompanylisthistory);
	}
	
	//shihuan 2017-03-19 历史优质企业列表查询 
	@Override
	public List<GoodcompanyList> queryCoodCompanyHistory(String autoId, int curNum, int pageNum) throws CoreException {
		return getGoodCompanyListDao().queryCoodCompanyHistory(autoId, curNum, pageNum);
	}

	//shihuan 2017-03-19 历史优质企业列表查询 条数
	@Override
	public int queryCoodCompanyHistoryCount(String autoId) throws CoreException {
		return getGoodCompanyListDao().queryCoodCompanyHistoryCount(autoId);
	}
	
	//shihuan 2017-03-19 优质企业名单批导入文件
	@Override
	public int insertCoodCompUpload(List<GoodcompanyList> obj,BatchfileInfo batchfileInfo) throws CoreException {
		int inputCounts = goodCompanyListDao.insertCoodCompUpload(obj);
		batchfileInfo.setInputCounts(new BigDecimal(inputCounts));
		batchFileInfoDao.insert(batchfileInfo);
		return inputCounts;
	}
	
	//shihuan 2017-03-19 历史优质企业列表查询 条数
	@Override
	public int queryByCompanyName(String companyName) throws CoreException {
		return getGoodCompanyListDao().queryByCompanyName(companyName);
	}
}