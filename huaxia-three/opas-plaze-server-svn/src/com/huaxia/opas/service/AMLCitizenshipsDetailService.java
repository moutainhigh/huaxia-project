package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLCitizenshipsDetail;

/**
 * 归属国信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLCitizenshipsDetailService {

	/**
	 * 保存归属国信息
	 * 
	 * @param citizenshipsDetail
	 *            归属国信息对象
	 * @return
	 */
	int save(AMLCitizenshipsDetail citizenshipsDetail);

}
