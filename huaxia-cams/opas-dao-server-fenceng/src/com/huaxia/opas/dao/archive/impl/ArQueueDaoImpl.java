package com.huaxia.opas.dao.archive.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.archive.ArQueueDao;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;

public class ArQueueDaoImpl extends AbstractDAO implements ArQueueDao {

	private static final long serialVersionUID = -6903892965443437483L;

	private static final String NAMESPACES = "ArQueue.";

	@Override
	public int queryArQueueCount(ArDetail arDetail) {
		return getSqlMap().queryForObject(NAMESPACES + "queryArQueueCount", arDetail);
	}

	@Override
	public List<ArDetail> queryArQueueList(ArDetail arDetail, int curNum, int pageNum) {
		List<ArDetail> list = new ArrayList<ArDetail>();
		list = getSqlMap().queryForList(NAMESPACES + "queryArQueueList", arDetail, curNum, pageNum);
		return list;
	}

	@Override
	public int updateArQueueToDel(ArDetail arDetail) {
		return getSqlMap().update(NAMESPACES + "updateArQueueToDel", arDetail);
	}

	@Override
	public int updateFileNoForAr(ArDetail arDetail) {
		return getSqlMap().update(NAMESPACES + "updateFileNoForAr", arDetail);
	}

	@Override
	public int insertArBatch(ArBatch arBatch) {
		return getSqlMap().insert(NAMESPACES + "insertArBatch", arBatch);
	}

	@Override
	public int updAndInsAll(ArDetail arDetail) {
		return getSqlMap().update(NAMESPACES + "updAndInsAll", arDetail);
	}

	@Override
	public int updateAppFlag(ArDetail arDetail) {
		return getSqlMap().update(NAMESPACES + "updateAppFlag", arDetail);
	}

	// 查询批量归档的件
	@Override
	public List<ArDetail> queryArDetailList(ArDetail arDetail) {
		return getSqlMap().queryForList(NAMESPACES + "queryArQueueList", arDetail);
	}

	@Override
	public void updateC1(ArDetail arDetail) {
		getSqlMap().update(NAMESPACES + "updateC1", arDetail);
	}

	@Override
	public Map<String, Object> queryMatchingFlag(Map appIdMap) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMatchingFlag", appIdMap);
	}

	@Override
	public Map<String, Object> querysynFlag(Map appIdMap) {
		return getSqlMap().queryForObject(NAMESPACES + "querysynFlag", appIdMap);
	}

	@Override
	public void updatesynFlag(Map appIdMap) {
		getSqlMap().update(NAMESPACES + "updatesynFlag", appIdMap);
	}

	@Override
	public int updateArchiveBatchNo(Map<String, String> params) {
		return getSqlMap().update(NAMESPACES + "updateArchiveBatchNo", params);
	}

}
