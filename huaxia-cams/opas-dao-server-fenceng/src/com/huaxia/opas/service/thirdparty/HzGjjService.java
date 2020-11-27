package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

public interface HzGjjService {

	List<Map<String, String>> queryHzVehiclePenaltyInfo(String appId);

	List<Map<String, String>> queryHzVehicleInfo(String appId);

	List<Map<String, String>> queryGjjLoadBrief(String appId);

	List<Map<String, String>> queryHzFyNaturalPerson(String appId);

	List<Map<String, String>> queryHzWaterAffairsInfo(String appId);

	List<Map<String, String>> queryHzRsjZxAc01(String appId);

	List<Map<String, String>> querySBfeeinfogridInfo(String appId);

	Map<String, String> queryGrxxInfo(String appId);

	List<Map<String, String>> queryGjjxxInfo(String appId);

}
