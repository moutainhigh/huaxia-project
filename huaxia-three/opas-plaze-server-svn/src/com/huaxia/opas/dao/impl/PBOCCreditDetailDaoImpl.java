package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCCreditDetailDao;
import com.huaxia.opas.mapper.PBOCCreditDetailMapper;

@Repository
public class PBOCCreditDetailDaoImpl implements PBOCCreditDetailDao {

	@Resource
	private PBOCCreditDetailMapper creditDetailMapper;
	
	public PBOCCreditDetailMapper getCreditDetailMapper() {
		return creditDetailMapper;
	}

	public void setCreditDetailMapper(PBOCCreditDetailMapper creditDetailMapper) {
		this.creditDetailMapper = creditDetailMapper;
	}
	
	@Override
	public int insertBatchForLoan(Map<String, Object> parameters) {
		return getCreditDetailMapper().insertBatchForLoan(parameters);
	}

	@Override
	public int insertBatchForLoancard(Map<String, Object> parameters) {
		return getCreditDetailMapper().insertBatchForLoancard(parameters);
	}

	@Override
	public int insertBatchForStandardLoancard(Map<String, Object> parameters) {
		return getCreditDetailMapper().insertBatchForStandardLoancard(parameters);
	}

	

}
