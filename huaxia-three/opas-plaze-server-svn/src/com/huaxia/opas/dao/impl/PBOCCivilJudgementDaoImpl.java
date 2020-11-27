package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCCivilJudgementDao;
import com.huaxia.opas.mapper.PBOCCivilJudgementMapper;

@Repository
public class PBOCCivilJudgementDaoImpl implements PBOCCivilJudgementDao {

	@Resource
	private PBOCCivilJudgementMapper civilJudgementMapper;

	public PBOCCivilJudgementMapper getCivilJudgementMapper() {
		return civilJudgementMapper;
	}

	public void setCivilJudgementMapper(PBOCCivilJudgementMapper civilJudgementMapper) {
		this.civilJudgementMapper = civilJudgementMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getCivilJudgementMapper().insertBatch(parameters);
	}
	
}
