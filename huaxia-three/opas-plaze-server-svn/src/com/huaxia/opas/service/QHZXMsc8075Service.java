package com.huaxia.opas.service;

import com.huaxia.opas.domain.QHZXMsc8075;

/**
 * 常贷客数据信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface QHZXMsc8075Service {

	/**
	 * 保存常贷客数据信息
	 * 
	 * @param msc8075
	 *            常贷客数据信息对象
	 * @return
	 */
	int save(QHZXMsc8075 msc8075);
	
	int queryCountMsc8075ByAppId(String appId);

}
