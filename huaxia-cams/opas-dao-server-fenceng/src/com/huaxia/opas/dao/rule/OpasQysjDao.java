package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasQysjDao {

	
	/**
	 * 
	 * @param appId
	 * @return
	 * 查询区域数据信息
	 * add by wjf
	 */

	List<Map<String, String>> queryXmInfoByAppId(String appId);

	List<Map<String, String>> querySzScoreInfoByAppId(String appId);

	List<Map<String, String>> querySzReportInfoByAppId(String appId);

	List<Map<String, String>> queryHzInfoByAppId(String appId);

	List<Map<String, String>> queryXmGjjInfoByAppId(String appId);

	List<Map<String, String>> queryWzInfoByAppId(String appId);

	List<Map<String, String>> queryYcInfoByAppId(String appId);

	List<Map<String, String>> queryNbInfoByAppId(String appId);
		
}