package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasYuShenDao {

	
	/**
	 * 
	 * @param appId
	 * @return
	 * 查询预审信息
	 * add by wjf
	 */

	List<Map<String, String>> queryYuShenInfoByAppId(String appId);
	
	
}