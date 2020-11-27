package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasPadDao {

	
	/**
	 * 
	 * @param appId
	 * @return
	 * 查询Pad信息
	 * add by wjf 2019/09/20
	 */

	//查询pad设备指纹信息
	List<Map<String, String>> queryPadInforByAppId(String appId);

	//查询当前设备Id下所有申请件的不同申请人证件号数量
	int queryIdNumberCountByDeviceId(Map<String,String> paramMap);

	//查询当前设备Id下决策时间7天内所有申请件的不同申请人证件号数量
	int queryIdNumberCountByDeviceId4Week(Map<String, String> paramMap);
	
	//查询当前设备Id下决策时间30天内所有申请件的不同申请人证件号数量
	int queryIdNumberCountByDeviceId4Month(Map<String, String> paramMap);
	//查询设备高风险命中情况
	List<Map<String, String>> queryPadANDRiskInfoByAppId(String appId);

	List<Map<String, String>> queryPadIOSRiskInfoByAppId(String appId);

	List<Map<String, String>> queryPadWAPRiskInfoByAppId(String appId);

	List<Map<String, String>> queryPadWEBRiskInfoByAppId(String appId);

	List<String> queryBodyCheckByAppId(String appId);

	List<String> queryPhotoCheckByAppId(String appId);

	
	
}