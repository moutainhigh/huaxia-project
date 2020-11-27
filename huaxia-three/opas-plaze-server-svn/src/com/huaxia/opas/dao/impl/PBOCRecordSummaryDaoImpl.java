package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCRecordSummaryDao;
import com.huaxia.opas.domain.QueryRecordSummary;
import com.huaxia.opas.mapper.PBOCRecordSummaryMapper;

@Repository
public class PBOCRecordSummaryDaoImpl implements PBOCRecordSummaryDao {

	@Resource
	private PBOCRecordSummaryMapper recordSummaryMapper;
	
	@Override
	public int insert(QueryRecordSummary entity) {
		return getRecordSummaryMapper().insert(entity);
	}

	public PBOCRecordSummaryMapper getRecordSummaryMapper() {
		return recordSummaryMapper;
	}

	public void setRecordSummaryMapper(PBOCRecordSummaryMapper recordSummaryMapper) {
		this.recordSummaryMapper = recordSummaryMapper;
	}

}
