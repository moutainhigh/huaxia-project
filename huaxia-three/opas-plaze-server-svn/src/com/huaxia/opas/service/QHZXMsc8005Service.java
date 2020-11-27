package com.huaxia.opas.service;

import com.huaxia.opas.domain.QHZXMsc8005;

/**
 * 好信度数据信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface QHZXMsc8005Service {

	/**
	 * 保存好信度数据信息
	 * 
	 * @param msc8005
	 *            好信度数据信息对象
	 * @return
	 */
	int save(QHZXMsc8005 msc8005);
	
	int queryCountMsc8005ByAppId(String appId);
}
