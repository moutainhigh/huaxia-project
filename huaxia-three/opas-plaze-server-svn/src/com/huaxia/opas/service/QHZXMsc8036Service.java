package com.huaxia.opas.service;

import com.huaxia.opas.domain.QHZXMsc8036;

/**
 * 风险度提示数据信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface QHZXMsc8036Service {

	/**
	 * 保存风险度提示数据信息
	 * 
	 * @param msc8036
	 *            风险度提示数据信息对象
	 * @return
	 */
	int save(QHZXMsc8036 msc8036);

	int queryCountMsc8036ByAppId(String appId);
}
