package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCOverdueAndFellbackDao;
import com.huaxia.opas.mapper.PBOCOverdueAndFellbackMapper;

@Repository
public class PBOCOverdueAndFellbackDaoImpl implements PBOCOverdueAndFellbackDao {

	@Resource
	private PBOCOverdueAndFellbackMapper overdueAndFellbackMapper;

	@Override
	public int insert(Map<String, Object> entity) {
		return getOverdueAndFellbackMapper().insert(entity);
	}

	public PBOCOverdueAndFellbackMapper getOverdueAndFellbackMapper() {
		return overdueAndFellbackMapper;
	}

	public void setOverdueAndFellbackMapper(PBOCOverdueAndFellbackMapper overdueAndFellbackMapper) {
		this.overdueAndFellbackMapper = overdueAndFellbackMapper;
	}
	
}
