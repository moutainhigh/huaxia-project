package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLAlterNativeSpelling;

/**
 * 其他可能拼写方式信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLAlterNativeSpellingService {

	/**
	 * 保存其他可能拼写方式信息
	 * 
	 * @param alterNativeSpelling
	 *            其他可能拼写方式信息对象
	 * @return
	 */
	int save(AMLAlterNativeSpelling alterNativeSpelling);

}
