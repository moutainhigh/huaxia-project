package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.CRMSpIfsCustCardDao;
import com.huaxia.opas.domain.CRMSpIfsCustCard;
import com.huaxia.opas.mapper.crm.CRMSpIfsCustCardMapper;

@Repository
public class CRMSpIfsCustCardDaoImpl implements CRMSpIfsCustCardDao {
	
	@Resource
	private CRMSpIfsCustCardMapper spIfsCustCardMapper;

	@Override
	public int insert(CRMSpIfsCustCard spIfsCustCard) {
		return getSpIfsCustCardMapper().insert(spIfsCustCard);
	}
	
	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getSpIfsCustCardMapper().insertBatch(parameters);
	}

	public CRMSpIfsCustCardMapper getSpIfsCustCardMapper() {
		return spIfsCustCardMapper;
	}

	public void setSpIfsCustCardMapper(CRMSpIfsCustCardMapper spIfsCustCardMapper) {
		this.spIfsCustCardMapper = spIfsCustCardMapper;
	}

}
