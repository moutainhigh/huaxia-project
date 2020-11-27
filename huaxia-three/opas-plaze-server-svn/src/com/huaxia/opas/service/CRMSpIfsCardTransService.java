package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCardTrans;

/**
 * 借记卡交易流水服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCardTransService {

	/**
	 * 保存借记卡交易流水信息
	 * 
	 * @param spIfsCardTrans
	 *            借记卡交易流水对象
	 * @return
	 */
	int save(CRMSpIfsCardTrans spIfsCardTrans);

	/**
	 * 批量保存借记卡交易流水信息
	 * 
	 * @param spIfsCardTransList
	 *            借记卡交易流水信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCardTrans> spIfsCardTransList);

}
