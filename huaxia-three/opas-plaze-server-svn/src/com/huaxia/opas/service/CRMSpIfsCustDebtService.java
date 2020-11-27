package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCustDebt;

/**
 * 客户全行负债服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCustDebtService {

	/**
	 * 保存客户全行负债信息
	 * 
	 * @param spIfsCustDebt
	 *            客户全行负债对象
	 * @return
	 */
	int save(CRMSpIfsCustDebt spIfsCustDebt);

	/**
	 * 批量保存客户全行负债信息
	 * 
	 * @param spIfsCustindexList
	 *            客户全行负债信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCustDebt> spIfsCustDebtList);

}
