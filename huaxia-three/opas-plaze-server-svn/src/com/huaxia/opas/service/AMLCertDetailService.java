package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLCertDetail;

/**
 * 证件信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLCertDetailService {

	/**
	 * 保存证件信息
	 * 
	 * @param certDetail
	 *            证件信息对象
	 * @return
	 */
	int save(AMLCertDetail certDetail);

}
