package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCustindex;

/**
 * 客户全行资产服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCustindexService {

	/**
	 * 保存客户全行资产信息
	 * 
	 * @param spIfsCustindex
	 *            客户全行资产对象
	 * @return
	 */
	int save(CRMSpIfsCustindex spIfsCustindex);

	/**
	 * 批量保存客户全行资产信息
	 * 
	 * @param spIfsCustindexList
	 *            客户全行资产对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCustindex> spIfsCustindexList);

}
