package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsLoanGrntDao;
import com.huaxia.opas.domain.CRMSpIfsLoanGrnt;
import com.huaxia.opas.mapper.crm.CRMSpIfsLoanGrntMapper;

@Repository
public class CRMSpIfsLoanGrntDaoImpl implements CRMSpIfsLoanGrntDao {

	@Resource
	private CRMSpIfsLoanGrntMapper spIfsLoanGrntMapper;
	
	@Override
	public int insert(CRMSpIfsLoanGrnt spIfsLoanGrnt) {
		return getSpIfsLoanGrntMapper().insert(spIfsLoanGrnt);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsLoanGrntMapper().insertBatch(parameters);
	}

	public CRMSpIfsLoanGrntMapper getSpIfsLoanGrntMapper() {
		return spIfsLoanGrntMapper;
	}

	public void setSpIfsLoanGrntMapper(CRMSpIfsLoanGrntMapper spIfsLoanGrntMapper) {
		this.spIfsLoanGrntMapper = spIfsLoanGrntMapper;
	}

}
