package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLKeywordsDetailDao;
import com.huaxia.opas.domain.AMLKeywordsDetail;
import com.huaxia.opas.mapper.aml.AMLKeywordsDetailMapper;

@Repository
public class AMLKeywordsDetailDaoImpl implements AMLKeywordsDetailDao {
	
	@Resource
	private AMLKeywordsDetailMapper keywordsDetailMapper;

	@Override
	public int insert(AMLKeywordsDetail keywordsDetail) {
		return getKeywordsDetailMapper().insert(keywordsDetail);
	}

	public AMLKeywordsDetailMapper getKeywordsDetailMapper() {
		return keywordsDetailMapper;
	}

	public void setKeywordsDetailMapper(AMLKeywordsDetailMapper keywordsDetailMapper) {
		this.keywordsDetailMapper = keywordsDetailMapper;
	}
	
}
