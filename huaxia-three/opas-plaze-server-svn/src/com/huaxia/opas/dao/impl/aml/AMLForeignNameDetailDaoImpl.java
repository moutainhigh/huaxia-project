package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLForeignNameDetailDao;
import com.huaxia.opas.domain.AMLForeignNameDetail;
import com.huaxia.opas.mapper.aml.AMLForeignNameDetailMapper;

@Repository
public class AMLForeignNameDetailDaoImpl implements AMLForeignNameDetailDao {
	
	@Resource
	private AMLForeignNameDetailMapper foreignNameDetailMapper;

	@Override
	public int insert(AMLForeignNameDetail foreignNameDetail) {
		return getForeignNameDetailMapper().insert(foreignNameDetail);
	}

	public AMLForeignNameDetailMapper getForeignNameDetailMapper() {
		return foreignNameDetailMapper;
	}

	public void setForeignNameDetailMapper(AMLForeignNameDetailMapper foreignNameDetailMapper) {
		this.foreignNameDetailMapper = foreignNameDetailMapper;
	}

}
