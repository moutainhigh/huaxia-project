package com.huaxia.opas.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.PBOCVehicleDao;
import com.huaxia.opas.mapper.PBOCVehicleMapper;

@Repository
public class PBOCVehicleDaoImpl implements PBOCVehicleDao {

	@Resource
	private PBOCVehicleMapper vehicleMapper;

	public PBOCVehicleMapper getVehicleMapper() {
		return vehicleMapper;
	}

	public void setVehicleMapper(PBOCVehicleMapper vehicleMapper) {
		this.vehicleMapper = vehicleMapper;
	}

	@Override
	public int insertBatch(Map<String, Object> parameters) {
		return getVehicleMapper().insertBatch(parameters);
	}
	
}
