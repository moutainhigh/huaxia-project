package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLImageUrl;

/**
 * 外部资源信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLImageUrlService {

	/**
	 * 保存外部资源信息
	 * 
	 * @param imageUrl
	 *            外部资源信息对象
	 * @return
	 */
	int save(AMLImageUrl imageUrl);

}
