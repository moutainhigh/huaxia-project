package com.huaxia.opas.service;

import com.huaxia.opas.domain.QHZXMsc8037;

/**
 * 好信欺诈度数据信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface QHZXMsc8037Service {

	/**
	 * 保存好信欺诈度数据信息
	 * 
	 * @param msc8037
	 *            好信欺诈度数据信息对象
	 * @return
	 */
	int save(QHZXMsc8037 msc8037);

	int queryCountMsc8037ByAppId(String appId);
}
