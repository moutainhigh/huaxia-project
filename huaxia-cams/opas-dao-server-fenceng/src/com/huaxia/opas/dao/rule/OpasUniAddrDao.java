package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasUniAddrDao {

	
	/**
	 * 
	 * @param appId
	 * @return
	 * 查询Pad信息
	 * add by wjf 2019/09/20
	 */

	//查询pad设备指纹信息
	List<Map<String, Object>> queryUniAddrInforByAppId(String appId);

}