package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasEnterpriseNetworkDao {

	
	/**
	 * 
	 * @param appId
	 * @return
	 * 查询企业网信息
	 * add by yuanquan
	 */
	List<Map<String,String>> queryEnterpriseNetworkStatus(String appId);
	
	
}