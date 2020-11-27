package com.huaxia.opas.dao.impl.aml;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.aml.AMLPassportsInfoDao;
import com.huaxia.opas.domain.AMLPassportsInfo;
import com.huaxia.opas.mapper.aml.AMLPassportsInfoMapper;

@Repository
public class AMLPassportsInfoDaoImpl implements AMLPassportsInfoDao {
	
	@Resource
	private AMLPassportsInfoMapper passportsInfoMapper;

	@Override
	public int insert(AMLPassportsInfo passportsInfo) {
		return getPassportsInfoMapper().insert(passportsInfo);
	}

	public AMLPassportsInfoMapper getPassportsInfoMapper() {
		return passportsInfoMapper;
	}

	public void setPassportsInfoMapper(AMLPassportsInfoMapper passportsInfoMapper) {
		this.passportsInfoMapper = passportsInfoMapper;
	}

}
