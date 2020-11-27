package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileLowRiskInfoDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.BatchfileLowRiskInfo;
import com.huaxia.opas.service.sysparm.BatchFileLowRiskInfoService;

public class BatchFileLowRiskInfoServiceImpl extends AbstractDAO implements BatchFileLowRiskInfoService, Serializable {

	private static final long serialVersionUID = 5757582418545513970L;
	
	@Resource(name = "batchFileLowRiskInfoDao")
	private BatchFileLowRiskInfoDao batchFileLowRiskInfoDao;
	
	public BatchFileLowRiskInfoDao getBatchFileLowRiskInfoDao() {
		return batchFileLowRiskInfoDao;
	}

	public void setBatchFileLowRiskInfoDao(BatchFileLowRiskInfoDao batchFileLowRiskInfoDao) {
		this.batchFileLowRiskInfoDao = batchFileLowRiskInfoDao;
	}

	@Override
	public int insert(BatchfileLowRiskInfo batchfileLowRiskInfo) {
		return  getBatchFileLowRiskInfoDao().insert(batchfileLowRiskInfo);
	}

	@Override
	public int insertSelective(BatchfileInfo batchFileInfo) {
		return  getBatchFileLowRiskInfoDao().insertSelective(batchFileInfo);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 
	@Override
	public List<BatchfileInfo> selectAll(BatchfileInfo batchFileInfo, int curNum, int pageNum) {
		return  getBatchFileLowRiskInfoDao().selectAll(batchFileInfo, curNum, pageNum);
	}

	//shihuan 2017-03-03 地址风险名单列表查询 条数
	@Override
	public int selectAllCount(BatchfileInfo batchFileInfo) {
		return  getBatchFileLowRiskInfoDao().selectAllCount(batchFileInfo);
	}
	
	@Override
	public int insertRist(BatchfileInfo batchFileInfo) {
		return  getBatchFileLowRiskInfoDao().insertRist(batchFileInfo);
	}

	@Override
	public int insertRistSelective(BatchfileInfo batchFileInfo) {
		return  getBatchFileLowRiskInfoDao().insertRistSelective(batchFileInfo);
	}

	//shihuan 2017-04-07 
	@Override
	public List<BatchfileInfo> selectRistAll(BatchfileInfo batchFileInfo, int curNum, int pageNum) {
		return  getBatchFileLowRiskInfoDao().selectRistAll(batchFileInfo, curNum, pageNum);
	}

	//shihuan 2017-04-07 
	@Override
	public int selectRistAllCount(BatchfileInfo batchFileInfo) {
		return  getBatchFileLowRiskInfoDao().selectRistAllCount(batchFileInfo);
	}

	@Override
	public String selectUser() {
		return batchFileLowRiskInfoDao.selectUser();
	}

	@Override
	public int updateTotalCount(Map<String, Object> args) {
		return batchFileLowRiskInfoDao.updateTotalCount(args);
	}

	@Override
	public int updateStatus(String status) {
		return batchFileLowRiskInfoDao.updateStatus(status);
	}
}
