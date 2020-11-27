package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.BatchFileInfoDao;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;

public class BatchFileInfoDaoImpl extends AbstractDAO implements BatchFileInfoDao {

	private static final long serialVersionUID = 5757582418545513970L;
	
	private static final String NAMESPACES = "BatchfileInfo.";

	@Override
	public int insert(BatchfileInfo batchfileinfo) {
		return getSqlMap().insert(NAMESPACES + "insert", batchfileinfo);
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
}
