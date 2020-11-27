package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXSpecialListForCellDao;
import com.huaxia.opas.domain.BRZXSpecialList;
import com.huaxia.opas.mapper.BRZXSpecialListForCellMapper;

@Repository
public class BRZXSpecialListForCellDaoImpl implements BRZXSpecialListForCellDao {

	@Resource
	private BRZXSpecialListForCellMapper specialListForCellMapper;

	@Override
	public int insert(BRZXSpecialList specialListForCell) {
		return getSpecialListForCellMapper().insert(specialListForCell);
	}

	public BRZXSpecialListForCellMapper getSpecialListForCellMapper() {
		return specialListForCellMapper;
	}

	public void setSpecialListForCellMapper(BRZXSpecialListForCellMapper specialListForCellMapper) {
		this.specialListForCellMapper = specialListForCellMapper;
	}

}
