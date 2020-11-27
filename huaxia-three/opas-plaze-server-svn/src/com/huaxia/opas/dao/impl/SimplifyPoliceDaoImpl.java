package com.huaxia.opas.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.SimplifyPoliceDao;
import com.huaxia.opas.domain.SimplifyPolice;
import com.huaxia.opas.mapper.SimplifyPoliceMapper;

@Repository
public class SimplifyPoliceDaoImpl implements SimplifyPoliceDao {
	
	@Resource
	private SimplifyPoliceMapper simplifyPoliceMapper;

	@Override
	public int insert(SimplifyPolice police) {
		return getSimplifyPoliceMapper().insert(police);
	}

	@Override
	public int selectCountByAppId(String appId) {
		return getSimplifyPoliceMapper().selectCountByAppId(appId);
	}

	@Override
	public int selectCountByCertNo(String certNo) {
		return getSimplifyPoliceMapper().selectCountByCertNo(certNo);
	}

	public SimplifyPoliceMapper getSimplifyPoliceMapper() {
		return simplifyPoliceMapper;
	}

	public void setSimplifyPoliceMapper(SimplifyPoliceMapper simplifyPoliceMapper) {
		this.simplifyPoliceMapper = simplifyPoliceMapper;
	}

}
