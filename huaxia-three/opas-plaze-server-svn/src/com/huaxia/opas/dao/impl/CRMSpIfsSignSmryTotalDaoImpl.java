package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsSignSmryTotalDao;
import com.huaxia.opas.domain.CRMSpIfsSignSmryTotal;
import com.huaxia.opas.mapper.crm.CRMSpIfsSignSmryTotalMapper;

@Repository
public class CRMSpIfsSignSmryTotalDaoImpl implements CRMSpIfsSignSmryTotalDao {

	@Resource
	private CRMSpIfsSignSmryTotalMapper spIfsSignSmryTotalMapper;
	
	@Override
	public int insert(CRMSpIfsSignSmryTotal spIfsSignSmryTotal) {
		return getSpIfsSignSmryTotalMapper().insert(spIfsSignSmryTotal);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsSignSmryTotalMapper().insertBatch(parameters);
	}

	public CRMSpIfsSignSmryTotalMapper getSpIfsSignSmryTotalMapper() {
		return spIfsSignSmryTotalMapper;
	}

	public void setSpIfsSignSmryTotalMapper(CRMSpIfsSignSmryTotalMapper spIfsSignSmryTotalMapper) {
		this.spIfsSignSmryTotalMapper = spIfsSignSmryTotalMapper;
	}

}
