package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLForeignNameDetail;

/**
 * 路透中文名称信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLForeignNameDetailService {

	/**
	 * 保存路透中文名称信息
	 * 
	 * @param foreignNameDetail
	 *            路透中文名称信息对象
	 * @return
	 */
	int save(AMLForeignNameDetail foreignNameDetail);

}
