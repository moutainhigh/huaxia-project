package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCTelPaymentDao;
import com.huaxia.opas.mapper.PBOCTelPaymentMapper;

@Repository
public class PBOCTelPaymentDaoImpl implements PBOCTelPaymentDao {

	@Resource
	private PBOCTelPaymentMapper telPaymentMapper;

	public PBOCTelPaymentMapper getTelPaymentMapper() {
		return telPaymentMapper;
	}

	public void setTelPaymentMapper(PBOCTelPaymentMapper telPaymentMapper) {
		this.telPaymentMapper = telPaymentMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getTelPaymentMapper().insertBatch(parameters);
	}
	
}
