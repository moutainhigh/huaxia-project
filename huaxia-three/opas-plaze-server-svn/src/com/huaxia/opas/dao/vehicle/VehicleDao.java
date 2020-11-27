package com.huaxia.opas.dao.vehicle;

import com.huaxia.opas.domain.vehicle.Vehicle;

public interface VehicleDao {
	
	
	int insert(Vehicle vehicle);
	
	
	int selectCountByAppId(String appId);

}
