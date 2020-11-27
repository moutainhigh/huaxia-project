package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCRecordInfoDao;
import com.huaxia.opas.mapper.PBOCRecordInfoMapper;

@Repository
public class PBOCRecordInfoDaoImpl implements PBOCRecordInfoDao {

	@Resource
	private PBOCRecordInfoMapper recordInfoMapper;

	@Override
	public int insert(Map<String, Object> entity) {
		return getRecordInfoMapper().insert(entity);
	}

	public PBOCRecordInfoMapper getRecordInfoMapper() {
		return recordInfoMapper;
	}

	public void setRecordInfoMapper(PBOCRecordInfoMapper recordInfoMapper) {
		this.recordInfoMapper = recordInfoMapper;
	}
	
}
