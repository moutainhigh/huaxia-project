package com.huaxia.opas.dao.impl.fico;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.huaxia.opas.dao.fico.FicoDao;
import com.huaxia.opas.domain.fico.Fico;
import com.huaxia.opas.mapper.ThirdVehicleMapper;
import com.huaxia.opas.mapper.fico.FicoMapper;

@Repository
public class FicoDaoImpl implements FicoDao{

	@Autowired
	FicoMapper ficoMapper;
	
	public FicoMapper getFicoMapper() {
		return ficoMapper;
	}

	public void setFicoMapper(FicoMapper ficoMapper) {
		this.ficoMapper = ficoMapper;
	}

	@Override
	public int getCountByAppId(String appId) {
		return ficoMapper.getCountByAppId(appId);
	}

	@Override
	public int insert(Fico fico) {
		return ficoMapper.insert(fico);
	}

	@Override
	public Fico selectConfirmPboc(String appId) {
		return ficoMapper.selectConfirmPboc(appId);
	}

	@Override
	public Map<String, String> selectBizInpApplC1JudgeFlag(Map<String, String> params) {
		return ficoMapper.selectBizInpApplC1JudgeFlag(params);
	}
	
}
