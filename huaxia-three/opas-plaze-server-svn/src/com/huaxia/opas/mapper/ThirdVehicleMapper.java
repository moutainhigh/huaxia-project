package com.huaxia.opas.mapper;

import com.huaxia.opas.domain.vehicle.Vehicle;

public interface ThirdVehicleMapper {

	
	int insert(Vehicle vehicle);
	
	
	int selectCountByAppId(String appId);
}
