package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.BRZXSpecialListForLmCellDao;
import com.huaxia.opas.domain.BRZXSpecialList;
import com.huaxia.opas.mapper.BRZXSpecialListForLmCellMapper;

@Repository
public class BRZXSpecialListForLmCellDaoImpl implements BRZXSpecialListForLmCellDao {

	@Resource
	private BRZXSpecialListForLmCellMapper specialListForLmCellMapper;

	@Override
	public int insert(BRZXSpecialList specialListForLmCell) {
		return getSpecialListForLmCellMapper().insert(specialListForLmCell);
	}

	public BRZXSpecialListForLmCellMapper getSpecialListForLmCellMapper() {
		return specialListForLmCellMapper;
	}

	public void setSpecialListForLmCellMapper(BRZXSpecialListForLmCellMapper specialListForLmCellMapper) {
		this.specialListForLmCellMapper = specialListForLmCellMapper;
	}

}
