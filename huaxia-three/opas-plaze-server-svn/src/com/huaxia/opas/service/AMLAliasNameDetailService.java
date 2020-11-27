package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLAliasNameDetail;

/**
 * 别名信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLAliasNameDetailService {

	/**
	 * 保存别名信息
	 * 
	 * @param aliasNameDetail
	 *            别名信息对象
	 * @return
	 */
	int save(AMLAliasNameDetail aliasNameDetail);

}
