package com.huaxia.opas.dao.archive.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.archive.ArBatchDao;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;

public class ArBatchDaoImpl extends AbstractDAO implements ArBatchDao {

	private static final long serialVersionUID = 2406685604209297143L;

	private static final String NAMESPACES = "ArBatch.";

	@Override
	public int queryArchiveCount(ArBatch arBatch) {
		return getSqlMap().queryForObject(NAMESPACES + "queryArchiveCount", arBatch);
	}

	@Override
	public List<ArBatch> queryArchiveList(ArBatch arBatch, int curNum, int pageNum) {
		List<ArBatch> list = new ArrayList<ArBatch>();
		list = getSqlMap().queryForList(NAMESPACES + "queryArchiveList", arBatch, curNum, pageNum);
		return list;
	}

	@Override
	public int queryArDetailsCount(ArDetail arDetail) {
		return getSqlMap().queryForObject(NAMESPACES + "queryArDetailsCount", arDetail);
	}

	@Override
	public List<ArDetail> queryArDetailsList(ArDetail arDetail, int curNum, int pageNum) {
		List<ArDetail> list = new ArrayList<ArDetail>();
		list = getSqlMap().queryForList(NAMESPACES + "queryArDetailsList", arDetail, curNum, pageNum);
		return list;
	}

}
