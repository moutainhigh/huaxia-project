package com.huaxia.plaze.ui.unicom.service;

import java.util.List;
import java.util.Map;

/**
 * 手机实名认证查询服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface OnlineTimeQueryService {

	List<Map<String, Integer>> queryCountByParams(Map<String, Object> args);

}
