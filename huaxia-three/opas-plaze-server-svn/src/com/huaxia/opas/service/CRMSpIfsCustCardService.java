package com.huaxia.opas.service;

import java.util.List;

import com.huaxia.opas.domain.CRMSpIfsCustCard;

/**
 * 借记卡基本信息服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface CRMSpIfsCustCardService {

	/**
	 * 保存借记卡基本信息
	 * 
	 * @param spIfsCustCard
	 *            借借记卡基本信息对象
	 * @return
	 */
	int save(CRMSpIfsCustCard spIfsCustCard);

	/**
	 * 批量保存借记卡基本信息
	 * 
	 * @param spIfsCustCardList
	 *            借记卡基本信息对象列表
	 * @return
	 */
	int saveBatch(List<CRMSpIfsCustCard> spIfsCustCardList);

}
