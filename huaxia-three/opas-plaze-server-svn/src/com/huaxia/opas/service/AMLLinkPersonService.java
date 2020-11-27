package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLLinkPerson;

/**
 * 相关人物信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLLinkPersonService {

	/**
	 * 保存相关人物信息
	 * 
	 * @param certDetail
	 *            相关人物信息对象
	 * @return
	 */
	int save(AMLLinkPerson linkPerson);

}
