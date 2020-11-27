package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLPassportsInfo;

/**
 * 护照信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLPassportsInfoService {

	/**
	 * 保存护照信息
	 * 
	 * @param passportsInfo
	 *            护照信息对象
	 * @return
	 */
	int save(AMLPassportsInfo passportsInfo);

}
