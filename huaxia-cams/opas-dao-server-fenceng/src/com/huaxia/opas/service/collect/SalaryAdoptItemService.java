package com.huaxia.opas.service.collect;

import com.huaxia.opas.domain.collect.SalaryAdoptItem;
import com.huaxia.opas.service.BaseService;

/**
 * 增强信息收入可采纳项服务
 * 
 * @author zhiguo.li
 *
 */
public interface SalaryAdoptItemService extends BaseService<SalaryAdoptItem> {

	/**
	 * 查询指定申请件编号的可采纳项对象
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	SalaryAdoptItem getByAppId(String appId);
	
	/**
	 * 51公积金基数查询
	 * @param appId
	 * @return
	 */
	String selectDepositBaseByAppId(String appId);
	/**
	 * 51公积金月缴额查询
	 * @param appId
	 * @return
	 */
	String selectDepositAmountByAppId(String appId);

}
