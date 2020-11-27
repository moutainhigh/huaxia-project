package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLFurtherInfoDao;
import com.huaxia.opas.domain.AMLFurtherInfo;
import com.huaxia.opas.mapper.aml.AMLFurtherInfoMapper;

@Repository
public class AMLFurtherInfoDaoImpl implements AMLFurtherInfoDao {
	
	@Resource
	private AMLFurtherInfoMapper furtherInfoMapper;

	@Override
	public int insert(AMLFurtherInfo furtherInfo) {
		return getFurtherInfoMapper().insert(furtherInfo);
	}

	public AMLFurtherInfoMapper getFurtherInfoMapper() {
		return furtherInfoMapper;
	}

	public void setFurtherInfoMapper(AMLFurtherInfoMapper furtherInfoMapper) {
		this.furtherInfoMapper = furtherInfoMapper;
	}

}
