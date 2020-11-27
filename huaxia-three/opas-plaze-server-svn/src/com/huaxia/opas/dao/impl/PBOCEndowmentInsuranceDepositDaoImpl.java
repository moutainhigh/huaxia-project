package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCEndowmentInsuranceDepositDao;
import com.huaxia.opas.mapper.PBOCEndowmentInsuranceDepositMapper;

@Repository
public class PBOCEndowmentInsuranceDepositDaoImpl implements PBOCEndowmentInsuranceDepositDao {

	@Resource
	private PBOCEndowmentInsuranceDepositMapper endowmentInsuranceDepositMapper;

	public PBOCEndowmentInsuranceDepositMapper getEndowmentInsuranceDepositMapper() {
		return endowmentInsuranceDepositMapper;
	}

	public void setEndowmentInsuranceDepositMapper(PBOCEndowmentInsuranceDepositMapper endowmentInsuranceDepositMapper) {
		this.endowmentInsuranceDepositMapper = endowmentInsuranceDepositMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getEndowmentInsuranceDepositMapper().insertBatch(parameters);
	}
	
}
