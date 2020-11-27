package com.huaxia.opas.service;

import com.huaxia.opas.domain.QHZXMsc8007;

/**
 * 地址通数据信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface QHZXMsc8007Service {

	/**
	 * 保存地址通数据信息
	 * 
	 * @param msc8007
	 *            地址通数据信息对象
	 * @return
	 */
	int save(QHZXMsc8007 msc8007);

	int queryCountMsc8007ByAppId(String appId);
}
