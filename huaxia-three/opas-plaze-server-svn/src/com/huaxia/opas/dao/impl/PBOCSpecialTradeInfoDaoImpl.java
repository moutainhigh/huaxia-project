package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCSpecialTradeInfoDao;
import com.huaxia.opas.mapper.PBOCSpecialTradeMapper;



@Repository
public class PBOCSpecialTradeInfoDaoImpl implements PBOCSpecialTradeInfoDao {

	@Resource
	private PBOCSpecialTradeMapper PBOCSpecialTradeMapper;
	
	
	public PBOCSpecialTradeMapper getPBOCSpecialTradeMapper() {
		return PBOCSpecialTradeMapper;
	}


	public void setPBOCSpecialTradeMapper(PBOCSpecialTradeMapper pBOCSpecialTradeMapper) {
		PBOCSpecialTradeMapper = pBOCSpecialTradeMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return PBOCSpecialTradeMapper.insertBatch(parameters);
	}
	
}
