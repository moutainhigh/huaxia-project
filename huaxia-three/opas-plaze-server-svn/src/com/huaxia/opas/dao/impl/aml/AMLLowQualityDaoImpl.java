package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLLowQualityDao;
import com.huaxia.opas.domain.AMLLowQuality;
import com.huaxia.opas.mapper.aml.AMLLowQualityMapper;

@Repository
public class AMLLowQualityDaoImpl implements AMLLowQualityDao {
	
	@Resource
	private AMLLowQualityMapper lowQualityMapper;

	@Override
	public int insert(AMLLowQuality lowQuality) {
		return getLowQualityMapper().insert(lowQuality);
	}

	public AMLLowQualityMapper getLowQualityMapper() {
		return lowQualityMapper;
	}

	public void setLowQualityMapper(AMLLowQualityMapper lowQualityMapper) {
		this.lowQualityMapper = lowQualityMapper;
	}

}
