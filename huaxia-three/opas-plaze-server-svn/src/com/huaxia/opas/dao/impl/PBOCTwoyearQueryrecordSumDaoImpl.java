package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCTwoyearQueryrecordSumDao;
import com.huaxia.opas.mapper.PBOCTwoyearQueryrecordSumMapper;

@Repository
public class PBOCTwoyearQueryrecordSumDaoImpl implements PBOCTwoyearQueryrecordSumDao {

	@Resource
	private PBOCTwoyearQueryrecordSumMapper twoyearQueryrecordSumMapper;

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getTwoyearQueryrecordSumMapper().insertBatch(parameters);
	}

	public PBOCTwoyearQueryrecordSumMapper getTwoyearQueryrecordSumMapper() {
		return twoyearQueryrecordSumMapper;
	}

	public void setTwoyearQueryrecordSumMapper(PBOCTwoyearQueryrecordSumMapper twoyearQueryrecordSumMapper) {
		this.twoyearQueryrecordSumMapper = twoyearQueryrecordSumMapper;
	}
	
}
