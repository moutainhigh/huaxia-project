package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCDissentInfoDao;
import com.huaxia.opas.mapper.PBOCDissentInfoMapper;

@Repository
public class PBOCDissentInfoDaoImpl implements PBOCDissentInfoDao {

	@Resource
	private PBOCDissentInfoMapper dissentInfoMapper;

	public PBOCDissentInfoMapper getDissentInfoMapper() {
		return dissentInfoMapper;
	}

	public void setDissentInfoMapper(PBOCDissentInfoMapper dissentInfoMapper) {
		this.dissentInfoMapper = dissentInfoMapper;
	}

	@Override
	public int insert(Map<String, Object> entity) {
		return getDissentInfoMapper().insert(entity);
	}

}
