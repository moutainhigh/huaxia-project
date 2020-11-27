package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCAccFundDao;
import com.huaxia.opas.mapper.PBOCAccFundMapper;

@Repository
public class PBOCAccFundDaoImpl implements PBOCAccFundDao {

	@Resource
	private PBOCAccFundMapper accFundMapper;

	public PBOCAccFundMapper getAccFundMapper() {
		return accFundMapper;
	}

	public void setAccFundMapper(PBOCAccFundMapper accFundMapper) {
		this.accFundMapper = accFundMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getAccFundMapper().insertBatch(parameters);
	}
	
}
