package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLDobsDetail;

/**
 * 出生日期信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLDobsDetailService {

	/**
	 * 保存出生日期信息
	 * 
	 * @param dobsDetail
	 *            出生日期信息对象
	 * @return
	 */
	int save(AMLDobsDetail dobsDetail);

}
