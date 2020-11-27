package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.service.sysparm.BatchFileInfoService;

public class BatchFileInfoServiceImpl extends AbstractDAO implements BatchFileInfoService, Serializable {

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "batchFileInfoDao")
	private BatchFileInfoDao batchFileInfoDao;
	
	
	public BatchFileInfoDao getBatchFileInfoDao() {
		return batchFileInfoDao;
	}

	public void setBatchFileInfoDao(BatchFileInfoDao batchFileInfoDao) {
		this.batchFileInfoDao = batchFileInfoDao;
	}

	@Override
	public int insert(BatchfileInfo batchFileInfo) {
		return  getBatchFileInfoDao().insert(batchFileInfo);
	}

	@Override
	public int insertSelective(BatchfileInfo batchFileInfo) {
		return  getBatchFileInfoDao().insertSelective(batchFileInfo);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<BatchfileInfo> selectAll(BatchfileInfo batchFileInfo, int curNum, int pageNum) {
		return  getBatchFileInfoDao().selectAll(batchFileInfo, curNum, pageNum);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int selectAllCount(BatchfileInfo batchFileInfo) {
		return  getBatchFileInfoDao().selectAllCount(batchFileInfo);
	}
	
	@Override
	public int insertRist(BatchfileInfo batchFileInfo) {
		return  getBatchFileInfoDao().insertRist(batchFileInfo);
	}

	@Override
	public int insertRistSelective(BatchfileInfo batchFileInfo) {
		return  getBatchFileInfoDao().insertRistSelective(batchFileInfo);
	}

	//shihuan 2017-04-07 
	@Override
	public List<BatchfileInfo> selectRistAll(BatchfileInfo batchFileInfo, int curNum, int pageNum) {
		return  getBatchFileInfoDao().selectRistAll(batchFileInfo, curNum, pageNum);
	}

	//shihuan 2017-04-07 
	@Override
	public int selectRistAllCount(BatchfileInfo batchFileInfo) {
		return  getBatchFileInfoDao().selectRistAllCount(batchFileInfo);
	}
}
