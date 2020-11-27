package com.huaxia.opas.dao.impl.brzx;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.brzx.SpecialListForGidDao;
import com.huaxia.opas.domain.BRZXSpecialList;
import com.huaxia.opas.mapper.brzx.SpecialListForGidMapper;

@Repository
public class SpecialListForGidDaoImpl implements SpecialListForGidDao {

	@Resource
	private SpecialListForGidMapper specialListForGidMapper;

	public SpecialListForGidMapper getSpecialListForGidMapper() {
		return specialListForGidMapper;
	}

	public void setSpecialListForGidMapper(SpecialListForGidMapper specialListForGidMapper) {
		this.specialListForGidMapper = specialListForGidMapper;
	}

	@Override
	public int insert(BRZXSpecialList specialList) {
		return getSpecialListForGidMapper().insert(specialList);
	}


}
