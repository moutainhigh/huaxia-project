package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLRecordIndexDao;
import com.huaxia.opas.domain.AMLRecordIndex;
import com.huaxia.opas.mapper.aml.AMLRecordIndexMapper;

@Repository
public class AMLRecordIndexDaoImpl implements AMLRecordIndexDao {
	
	@Resource
	private AMLRecordIndexMapper recordIndexMapper;

	@Override
	public int insert(AMLRecordIndex recordIndex) {
		return getRecordIndexMapper().insert(recordIndex);
	}

	public AMLRecordIndexMapper getRecordIndexMapper() {
		return recordIndexMapper;
	}

	public void setRecordIndexMapper(AMLRecordIndexMapper recordIndexMapper) {
		this.recordIndexMapper = recordIndexMapper;
	}

}
