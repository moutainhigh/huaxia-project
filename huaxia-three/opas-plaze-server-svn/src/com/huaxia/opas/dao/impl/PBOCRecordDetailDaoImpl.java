package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCRecordDetailDao;
import com.huaxia.opas.mapper.PBOCRecordDetailMapper;

@Repository
public class PBOCRecordDetailDaoImpl implements PBOCRecordDetailDao {

	@Resource
	private PBOCRecordDetailMapper recordDetailMapper;

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getRecordDetailMapper().insertBatch(parameters);
	}

	public PBOCRecordDetailMapper getRecordDetailMapper() {
		return recordDetailMapper;
	}

	public void setRecordDetailMapper(PBOCRecordDetailMapper recordDetailMapper) {
		this.recordDetailMapper = recordDetailMapper;
	}
	
}
