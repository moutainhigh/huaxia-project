package com.huaxia.opas.service;

import com.huaxia.opas.domain.QHZXMsc8107;

/**
 * 好信一鉴通数据信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface QHZXMsc8107Service {

	/**
	 * 保存好信一鉴通数据信息
	 * 
	 * @param spIfsCardTransList
	 *            好信一鉴通数据信息对象
	 * @return
	 */
	int save(QHZXMsc8107 msc8107);
	
	int queryCountMsc8107ByAppId(String appId);

}
