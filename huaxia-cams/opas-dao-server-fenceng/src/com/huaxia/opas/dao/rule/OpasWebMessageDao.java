package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasWebMessageDao {

	
	/**
	 * 
	 * @param appId
	 * @return
	 * 查询网申客户行为采集信息
	 * add by wjf 2019/09/20
	 */
	
	List<Map<String, Object>> queryWebCustomerBehaviorInfoByAppId(String appId);

}