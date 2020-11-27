package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLInfoUseAreaDao;
import com.huaxia.opas.domain.AMLInfoUseArea;
import com.huaxia.opas.mapper.aml.AMLInfoUseAreaMapper;

@Repository
public class AMLInfoUseAreaDaoImpl implements AMLInfoUseAreaDao {
	
	@Resource
	private AMLInfoUseAreaMapper infoUseAreaMapper;

	@Override
	public int insert(AMLInfoUseArea infoUseArea) {
		return getInfoUseAreaMapper().insert(infoUseArea);
	}

	public AMLInfoUseAreaMapper getInfoUseAreaMapper() {
		return infoUseAreaMapper;
	}

	public void setInfoUseAreaMapper(AMLInfoUseAreaMapper infoUseAreaMapper) {
		this.infoUseAreaMapper = infoUseAreaMapper;
	}

}
