package com.huaxia.opas.dao.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.huaxia.opas.dao.PBOCAssurerRepayInfoDao;
import com.huaxia.opas.mapper.PBOCAssurerRepayInfoMapper;

@Repository
public class PBOCAssurerRepayInfoDaoImpl implements PBOCAssurerRepayInfoDao {

	@Resource
	private PBOCAssurerRepayInfoMapper assurerRepayInfoMapper;
	
	public PBOCAssurerRepayInfoMapper getAssurerRepayInfoMapper() {
		return assurerRepayInfoMapper;
	}

	public void setAssurerRepayInfoMapper(PBOCAssurerRepayInfoMapper assurerRepayInfoMapper) {
		this.assurerRepayInfoMapper = assurerRepayInfoMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return  getAssurerRepayInfoMapper().insertBatch(parameters);
	}
	
}
