package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLLowQuality;

/**
 * 其他别名信息服务接口
 * 
 * .
 * @author zhiguo.li
 *
 */
public interface AMLLowQualityService {

	/**
	 * 保存其他别名信息
	 * 
	 * @param lowQuality
	 *            其他别名信息对象
	 * @return
	 */
	int save(AMLLowQuality lowQuality);

}
