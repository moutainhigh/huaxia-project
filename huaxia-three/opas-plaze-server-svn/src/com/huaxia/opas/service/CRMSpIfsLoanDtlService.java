package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsLoanDtl;

/**
 * 个贷还款明细服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsLoanDtlService {

	/**
	 * 保存个贷还款明细信息
	 * 
	 * @param spIfsLoanDtl
	 *            个贷还款明细对象
	 * @return
	 */
	int save(CRMSpIfsLoanDtl spIfsLoanDtl);

	/**
	 * 批量保存个贷还款明细信息
	 * 
	 * @param spIfsLoanDtlList
	 *            个贷还款明细信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsLoanDtl> spIfsLoanDtlList);

}
