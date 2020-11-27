package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLKeywordsDetail;

/**
 * 制裁机构信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLKeywordsDetailService {

	/**
	 * 保存制裁机构信息
	 * 
	 * @param keywordsDetail
	 *            制裁机构信息对象
	 * @return
	 */
	int save(AMLKeywordsDetail keywordsDetail);

}
