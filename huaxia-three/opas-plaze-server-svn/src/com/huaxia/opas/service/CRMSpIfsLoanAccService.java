package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsLoanAcc;

/**
 * 个贷账户服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsLoanAccService {

	/**
	 * 保存个贷账户信息
	 * 
	 * @param spIfsLoanAcc
	 *            个贷账户对象
	 * @return
	 */
	int save(CRMSpIfsLoanAcc spIfsLoanAcc);

	/**
	 * 批量保存个贷账户信息
	 * 
	 * @param spIfsLoanAccList
	 *            个贷账户信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsLoanAcc> spIfsLoanAccList);

}
