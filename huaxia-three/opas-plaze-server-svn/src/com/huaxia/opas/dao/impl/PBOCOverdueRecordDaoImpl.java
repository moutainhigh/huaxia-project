package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCOverdueRecordDao;
import com.huaxia.opas.mapper.PBOCOverdueRecordMapper;

@Repository
public class PBOCOverdueRecordDaoImpl implements PBOCOverdueRecordDao {

	@Resource
	private PBOCOverdueRecordMapper overdueRecordMapper;
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getOverdueRecordMapper().insertBatch(parameters);
	}

	public PBOCOverdueRecordMapper getOverdueRecordMapper() {
		return overdueRecordMapper;
	}

	public void setOverdueRecordMapper(PBOCOverdueRecordMapper overdueRecordMapper) {
		this.overdueRecordMapper = overdueRecordMapper;
	}

}
