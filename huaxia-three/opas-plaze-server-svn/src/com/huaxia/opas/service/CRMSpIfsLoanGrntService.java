package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsLoanGrnt;

/**
 * 个贷担保信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsLoanGrntService {

	/**
	 * 保存个贷担保信息
	 * 
	 * @param spIfsLoanGrnt
	 *            个贷担保信息对象
	 * @return
	 */
	int save(CRMSpIfsLoanGrnt spIfsLoanGrnt);
	
	/**
	 * 批量保存个贷担保信息
	 * 
	 * @param spIfsLoanGrntList
	 *            个贷担保信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsLoanGrnt> spIfsLoanGrntList);

}
