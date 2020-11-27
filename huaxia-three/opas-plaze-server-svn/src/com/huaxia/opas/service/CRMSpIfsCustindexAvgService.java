package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCustindexAvg;

/**
 * 客户全行资产日均服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCustindexAvgService {

	/**
	 * 保存客户全行资产日均信息
	 * 
	 * @param spIfsCustindexAvg
	 *            客户全行资产日均对象
	 * @return
	 */
	int save(CRMSpIfsCustindexAvg spIfsCustindexAvg);

	/**
	 * 批量保存客户全行资产日均信息
	 * 
	 * @param spIfsCustindexList
	 *            客户全行资产日均信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCustindexAvg> spIfsCustindexAvgList);

}
