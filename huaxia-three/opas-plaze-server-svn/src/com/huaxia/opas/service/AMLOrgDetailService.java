package com.huaxia.opas.service;

import com.huaxia.opas.domain.AMLOrgDetail;

/**
 * 证相关组织机构信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface AMLOrgDetailService {

	/**
	 * 保存相关组织机构信息
	 * 
	 * @param orgDetail
	 *            相关组织机构信息对象
	 * @return
	 */
	int save(AMLOrgDetail orgDetail);

}
