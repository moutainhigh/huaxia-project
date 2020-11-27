package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLDobsDetailDao;
import com.huaxia.opas.domain.AMLDobsDetail;
import com.huaxia.opas.mapper.aml.AMLDobsDetailMapper;

@Repository
public class AMLDobsDetailDaoImpl implements AMLDobsDetailDao {
	
	@Resource
	private AMLDobsDetailMapper dobsDetailMapper;
	
	@Override
	public int insert(AMLDobsDetail dobsDetail) {
		return getDobsDetailMapper().insert(dobsDetail);
	}

	public AMLDobsDetailMapper getDobsDetailMapper() {
		return dobsDetailMapper;
	}

	public void setDobsDetailMapper(AMLDobsDetailMapper dobsDetailMapper) {
		this.dobsDetailMapper = dobsDetailMapper;
	}

}
