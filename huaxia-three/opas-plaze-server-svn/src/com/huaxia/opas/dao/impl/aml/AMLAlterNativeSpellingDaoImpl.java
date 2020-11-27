package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLAlterNativeSpellingDao;
import com.huaxia.opas.domain.AMLAlterNativeSpelling;
import com.huaxia.opas.mapper.aml.AMLAlterNativeSpellingMapper;

@Repository
public class AMLAlterNativeSpellingDaoImpl implements AMLAlterNativeSpellingDao {
	
	@Resource
	private AMLAlterNativeSpellingMapper alterNativeSpellingMapper;

	@Override
	public int insert(AMLAlterNativeSpelling alterNativeSpelling) {
		return getAlterNativeSpellingMapper().insert(alterNativeSpelling);
	}

	public AMLAlterNativeSpellingMapper getAlterNativeSpellingMapper() {
		return alterNativeSpellingMapper;
	}

	public void setAlterNativeSpellingMapper(AMLAlterNativeSpellingMapper alterNativeSpellingMapper) {
		this.alterNativeSpellingMapper = alterNativeSpellingMapper;
	}

}
