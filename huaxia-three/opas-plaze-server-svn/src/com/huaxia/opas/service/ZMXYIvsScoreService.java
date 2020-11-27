package com.huaxia.opas.service;

import com.huaxia.opas.domain.ZMXYIvsScore;

/**
 * 反欺诈信息验证服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface ZMXYIvsScoreService {

	/**
	 * 保存反欺诈信息验证信息
	 * 
	 * @param ivsScore
	 *            反欺诈信息验证对象
	 * @return
	 */
	int save(ZMXYIvsScore ivsScore);

	int queryCountZMXYIvsScoreByAppId(String appId);
}
