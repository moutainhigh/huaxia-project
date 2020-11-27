package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsLoanAccAvg;

/**
 * 个贷账户月日均服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsLoanAccAvgService {

	/**
	 * 保存个贷账户月日均信息
	 * 
	 * @param spIfsLoanAccAvg
	 *            个贷账户月日均对象
	 * @return
	 */
	int save(CRMSpIfsLoanAccAvg spIfsLoanAccAvg);

	/**
	 * 批量保存个贷账户月日均信息
	 * 
	 * @param spIfsLoanAccAvgList
	 *            个贷账户月日均信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsLoanAccAvg> spIfsLoanAccAvgList);

}
