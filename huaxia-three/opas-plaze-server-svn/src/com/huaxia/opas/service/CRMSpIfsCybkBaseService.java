package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCybkBase;

/**
 * 网银基本信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCybkBaseService {

	/**
	 * 保存网银基本信息
	 * 
	 * @param spIfsCybkBase
	 *            网银基本信息对象
	 * @return
	 */
	int save(CRMSpIfsCybkBase spIfsCybkBase);

	/**
	 * 批量保存网银基本信息
	 * 
	 * @param spIfsCybkBaseList
	 *            网银基本信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCybkBase> spIfsCybkBaseList);

}
