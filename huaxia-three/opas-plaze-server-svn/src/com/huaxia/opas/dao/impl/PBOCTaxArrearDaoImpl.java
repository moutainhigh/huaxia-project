package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCTaxArrearDao;
import com.huaxia.opas.mapper.PBOCTaxArrearMapper;

@Repository
public class PBOCTaxArrearDaoImpl implements PBOCTaxArrearDao {

	@Resource
	private PBOCTaxArrearMapper taxArrearMapper;

	public PBOCTaxArrearMapper getTaxArrearMapper() {
		return taxArrearMapper;
	}

	public void setTaxArrearMapper(PBOCTaxArrearMapper taxArrearMapper) {
		this.taxArrearMapper = taxArrearMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getTaxArrearMapper().insertBatch(parameters);
	}
	
}
