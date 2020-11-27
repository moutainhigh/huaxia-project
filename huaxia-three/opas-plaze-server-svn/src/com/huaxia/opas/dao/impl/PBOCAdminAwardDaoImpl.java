package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCAdminAwardDao;
import com.huaxia.opas.mapper.PBOCAdminAwardMapper;

@Repository
public class PBOCAdminAwardDaoImpl implements PBOCAdminAwardDao {

	@Resource
	private PBOCAdminAwardMapper adminAwardMapper;

	public PBOCAdminAwardMapper getAdminAwardMapper() {
		return adminAwardMapper;
	}

	public void setAdminAwardMapper(PBOCAdminAwardMapper adminAwardMapper) {
		this.adminAwardMapper = adminAwardMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getAdminAwardMapper().insertBatch(parameters);
	}
	
}
