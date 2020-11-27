package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCCreditCueDao;
import com.huaxia.opas.mapper.PBOCCreditCueMapper;

@Repository
public class PBOCCreditCueDaoImpl implements PBOCCreditCueDao {

	@Resource
	private PBOCCreditCueMapper creditCueMapper;

	public PBOCCreditCueMapper getCreditCueMapper() {
		return creditCueMapper;
	}

	public void setCreditCueMapper(PBOCCreditCueMapper creditCueMapper) {
		this.creditCueMapper = creditCueMapper;
	}
	
	@Override
	public int insert(Map<String, Object> entity) {
		return getCreditCueMapper().insert(entity);
	}
	@Override
	public int insertScoreElesMapList(Map<String, Object> prarmMap) {
		return getCreditCueMapper().insertScoreElesMapList(prarmMap);
	}	
	
}
