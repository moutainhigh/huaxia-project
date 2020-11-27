package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCLatestMonthQueryorgSumDao;
import com.huaxia.opas.mapper.PBOCLatestMonthQueryorgSumMapper;

@Repository
public class PBOCLatestMonthQueryorgSumDaoImpl implements PBOCLatestMonthQueryorgSumDao {

	@Resource
	private PBOCLatestMonthQueryorgSumMapper latestMonthQueryorgSumMapper;

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getLatestMonthQueryorgSumMapper().insertBatch(parameters);
	}

	public PBOCLatestMonthQueryorgSumMapper getLatestMonthQueryorgSumMapper() {
		return latestMonthQueryorgSumMapper;
	}

	public void setLatestMonthQueryorgSumMapper(PBOCLatestMonthQueryorgSumMapper latestMonthQueryorgSumMapper) {
		this.latestMonthQueryorgSumMapper = latestMonthQueryorgSumMapper;
	}
	
}
