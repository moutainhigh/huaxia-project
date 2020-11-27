package com.huaxia.opas.dao.tripartite.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.tripartite.SeaAirDao;
import com.huaxia.opas.domain.tripartite.SeaAir;
import com.huaxia.opas.mapper.tripartite.SeaAirMapper;
@Repository
public class SeaAirDaoImpl implements SeaAirDao {

	@Resource
	private SeaAirMapper seaAirMapper;
	public SeaAirMapper getSeaAirMapper() {
		return seaAirMapper;
	}
	public void setSeaAirMapper(SeaAirMapper seaAirMapper) {
		this.seaAirMapper = seaAirMapper;
	}
	@Override
	public void insertSeaAirData(SeaAir seaAir) {
		seaAirMapper.insertSeaAirData(seaAir);
	}
	@Override
	public int selectCountByAppId(String appId) {
		return seaAirMapper.selectCountByAppId(appId);
	}
	@Override
	public void updateCardC1SeaMemberId(Map<String, Object> params) {
		seaAirMapper.updateCardC1SeaMemberId(params);
	}
	@Override
	public void updateCardC2SeaMemberId(Map<String, Object> params) {
		seaAirMapper.updateCardC2SeaMemberId(params);
	}

}