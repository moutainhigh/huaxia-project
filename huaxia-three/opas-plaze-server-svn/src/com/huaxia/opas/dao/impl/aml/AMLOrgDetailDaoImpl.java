package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLOrgDetailDao;
import com.huaxia.opas.domain.AMLOrgDetail;
import com.huaxia.opas.mapper.aml.AMLOrgDetailMapper;

@Repository
public class AMLOrgDetailDaoImpl implements AMLOrgDetailDao {
	
	@Resource
	private AMLOrgDetailMapper orgDetailMapper;

	@Override
	public int insert(AMLOrgDetail orgDetail) {
		return getOrgDetailMapper().insert(orgDetail);
	}

	public AMLOrgDetailMapper getOrgDetailMapper() {
		return orgDetailMapper;
	}

	public void setOrgDetailMapper(AMLOrgDetailMapper orgDetailMapper) {
		this.orgDetailMapper = orgDetailMapper;
	}

}
