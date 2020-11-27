package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCSalvationDao;
import com.huaxia.opas.mapper.PBOCSalvationDaoMapper;

@Repository
public class PBOCSalvationDaoImpl implements PBOCSalvationDao {

	@Resource
	private PBOCSalvationDaoMapper salvationDaoMapper;

	public PBOCSalvationDaoMapper getSalvationDaoMapper() {
		return salvationDaoMapper;
	}

	public void setSalvationDaoMapper(PBOCSalvationDaoMapper salvationDaoMapper) {
		this.salvationDaoMapper = salvationDaoMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSalvationDaoMapper().insertBatch(parameters);
	}
	
}
