package com.huaxia.opas.dao.impl.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.vehicle.VehicleDao;
import com.huaxia.opas.domain.vehicle.Vehicle;
import com.huaxia.opas.mapper.ThirdVehicleMapper;

@Repository
public class VehicleDaoImpl implements VehicleDao {

	@Autowired
	ThirdVehicleMapper veMapper;
	
	@Override
	public int insert(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return veMapper.insert(vehicle);
	}

	@Override
	public int selectCountByAppId(String appId) {
		// TODO Auto-generated method stub
		return veMapper.selectCountByAppId(appId);
	}

}
