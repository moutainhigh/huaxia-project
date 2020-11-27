package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.service.sqlmap.SqlMap;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileLowRiskInfoDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.BatchfileLowRiskInfo;

public class BatchFileLowRiskInfoDaoImpl extends AbstractDAO implements BatchFileLowRiskInfoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NAMESPACES = "BatchfileLowRiskInfo.";

	@Override
	public int insert(BatchfileLowRiskInfo batchfileLowRiskInfo) {
		return getSqlMap().insert(NAMESPACES + "insert", batchfileLowRiskInfo);
	}

	@Override
	public int insertSelective(BatchfileInfo batchfileinfo) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", batchfileinfo);
	}

	
	//shihuan 2017-04-07 
	@Override
	public List<BatchfileInfo> selectAll(BatchfileInfo batchfileinfo, int curNum, int pageNum) {

		List<BatchfileInfo> list = new ArrayList<BatchfileInfo>();

		list = getSqlMap().queryForList(NAMESPACES + "selectAll", batchfileinfo, curNum, pageNum);

		return list;
	}

	//shihuan 2017-04-07 
	@Override
	public int selectAllCount(BatchfileInfo batchfileinfo) {
		return getSqlMap().queryForObject(NAMESPACES + "selectAllCount", batchfileinfo);
	}

	
	@Override
	public int insertRist(BatchfileInfo batchfileinfo) {
		return getSqlMap().insert(NAMESPACES + "insertRist", batchfileinfo);
	}

	@Override
	public int insertRistSelective(BatchfileInfo batchfileinfo) {
		return getSqlMap().insert(NAMESPACES + "insertRistSelective", batchfileinfo);
	}

	
	//shihuan 2017-04-07 
	@Override
	public List<BatchfileInfo> selectRistAll(BatchfileInfo batchfileinfo, int curNum, int pageNum) {

		List<BatchfileInfo> list = new ArrayList<BatchfileInfo>();

		list = getSqlMap().queryForList(NAMESPACES + "selectRistAll", batchfileinfo, curNum, pageNum);

		return list;
	}

	//shihuan 2017-04-07 
	@Override
	public int selectRistAllCount(BatchfileInfo batchfileinfo) {
		return getSqlMap().queryForObject(NAMESPACES + "selectRistAllCount", batchfileinfo);
	}

	@Override
	public String selectUser() {
		return getSqlMap().queryForObject(NAMESPACES + "selectUser");
	}

	@Override
	public int updateTotalCount(Map<String, Object> args) {
		return getSqlMap().update(NAMESPACES + "updateTotalCount", args);
	}

	@Override
	public int updateStatus(String status) {
		return getSqlMap().update(NAMESPACES + "updateStatus", status);
	}
}
