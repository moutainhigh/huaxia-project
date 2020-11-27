package com.huaxia.opas.service;

import com.huaxia.opas.domain.ZMXYScore;

/**
 * 芝麻信用评分服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface ZMXYScoreService {

	/**
	 * 保存芝麻信用评分信息
	 * 
	 * @param score
	 *            芝麻信用评分对象
	 * @return
	 */
	int save(ZMXYScore score);

	int queryCountZMXYScoreByAppId(String appId);

}
