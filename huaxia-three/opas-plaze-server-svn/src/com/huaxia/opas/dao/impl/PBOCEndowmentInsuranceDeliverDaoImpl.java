package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCEndowmentInsuranceDeliverDao;
import com.huaxia.opas.mapper.PBOCEndowmentInsuranceDeliverMapper;

@Repository
public class PBOCEndowmentInsuranceDeliverDaoImpl implements PBOCEndowmentInsuranceDeliverDao {

	@Resource
	private PBOCEndowmentInsuranceDeliverMapper endowmentInsuranceDeliverMapper;

	public PBOCEndowmentInsuranceDeliverMapper getEndowmentInsuranceDeliverMapper() {
		return endowmentInsuranceDeliverMapper;
	}

	public void setEndowmentInsuranceDeliverMapper(PBOCEndowmentInsuranceDeliverMapper endowmentInsuranceDeliverMapper) {
		this.endowmentInsuranceDeliverMapper = endowmentInsuranceDeliverMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getEndowmentInsuranceDeliverMapper().insertBatch(parameters);
	}
	
}
