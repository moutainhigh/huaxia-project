package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsLoanAccAvgDao;
import com.huaxia.opas.domain.CRMSpIfsLoanAccAvg;
import com.huaxia.opas.mapper.crm.CRMSpIfsLoanAccAvgMapper;

@Repository
public class CRMSpIfsLoanAccAvgDaoImpl implements CRMSpIfsLoanAccAvgDao {

	@Resource
	private CRMSpIfsLoanAccAvgMapper spIfsLoanAccAvgMapper;
	
	@Override
	public int insert(CRMSpIfsLoanAccAvg spIfsLoanAccAvg) {
		return getSpIfsLoanAccAvgMapper().insert(spIfsLoanAccAvg);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsLoanAccAvgMapper().insertBatch(parameters);
	}

	public CRMSpIfsLoanAccAvgMapper getSpIfsLoanAccAvgMapper() {
		return spIfsLoanAccAvgMapper;
	}

	public void setSpIfsLoanAccAvgMapper(CRMSpIfsLoanAccAvgMapper spIfsLoanAccAvgMapper) {
		this.spIfsLoanAccAvgMapper = spIfsLoanAccAvgMapper;
	}

}
