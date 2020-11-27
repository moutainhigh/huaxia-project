package com.huaxia.plaze.ui.setting.service;

import java.util.List;
import java.util.Map;

/**
 * 人行查询服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface PbocQueryService {

	List<Map<String, Integer>> queryCountByParams(Map<String, Object> args);
//	
	int mergeQueryAuthorizeSetting(Map<String, Object> args);
	
	Map<String, String> queryAuthorizeSettingByParams(Map<String, Object> args);
	
}
