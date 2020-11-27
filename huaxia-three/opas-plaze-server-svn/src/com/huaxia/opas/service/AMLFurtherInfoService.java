package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLFurtherInfo;

/**
 * 制裁信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLFurtherInfoService {

	/**
	 * 保存制裁信息
	 * 
	 * @param furtherInfo
	 *            制裁信息对象
	 * @return
	 */
	int save(AMLFurtherInfo furtherInfo);

}
