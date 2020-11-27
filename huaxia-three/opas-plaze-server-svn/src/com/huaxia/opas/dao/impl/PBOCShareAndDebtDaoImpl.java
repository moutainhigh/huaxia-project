package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCShareAndDebtDao;
import com.huaxia.opas.mapper.PBOCShareAndDebtMapper;

@Repository
public class PBOCShareAndDebtDaoImpl implements PBOCShareAndDebtDao {

	@Resource
	private PBOCShareAndDebtMapper shareAndDebtMapper;

	@Override
	public int insert(Map<String, Object> entity) {
		return getShareAndDebtMapper().insert(entity);
	}

	public PBOCShareAndDebtMapper getShareAndDebtMapper() {
		return shareAndDebtMapper;
	}

	public void setShareAndDebtMapper(PBOCShareAndDebtMapper shareAndDebtMapper) {
		this.shareAndDebtMapper = shareAndDebtMapper;
	}
	
}
