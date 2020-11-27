package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCLatestMonthQueryrecordSumDao;
import com.huaxia.opas.mapper.PBOCLatestMonthQueryrecordSumMapper;

@Repository
public class PBOCLatestMonthQueryrecordSumDaoImpl implements PBOCLatestMonthQueryrecordSumDao {

	@Resource
	private PBOCLatestMonthQueryrecordSumMapper latestMonthQueryrecordSumMapper;

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getLatestMonthQueryrecordSumMapper().insertBatch(parameters);
	}

	public PBOCLatestMonthQueryrecordSumMapper getLatestMonthQueryrecordSumMapper() {
		return latestMonthQueryrecordSumMapper;
	}

	public void setLatestMonthQueryrecordSumMapper(PBOCLatestMonthQueryrecordSumMapper latestMonthQueryrecordSumMapper) {
		this.latestMonthQueryrecordSumMapper = latestMonthQueryrecordSumMapper;
	}
	
}
