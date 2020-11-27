package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCGuaranteeInfoDao;
import com.huaxia.opas.mapper.PBOCGuaranteeInfoMapper;

@Repository
public class PBOCGuaranteeInfoDaoImpl implements PBOCGuaranteeInfoDao {

	@Resource
	private PBOCGuaranteeInfoMapper guaranteeInfoMapper;

	public PBOCGuaranteeInfoMapper getGuaranteeInfoMapper() {
		return guaranteeInfoMapper;
	}

	public void setGuaranteeInfoMapper(PBOCGuaranteeInfoMapper guaranteeInfoMapper) {
		this.guaranteeInfoMapper = guaranteeInfoMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getGuaranteeInfoMapper().insertBatch(parameters);
	}
	
}
