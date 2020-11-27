package com.huaxia.opas.dao.collect;

import com.huaxia.opas.dao.DAO;
import com.huaxia.opas.domain.collect.SalaryAdoptItem;

/**
 * 增强信息收入可采纳项接口
 * 
 * @author zhiguo.li
 *
 */
public interface SalaryAdoptItemDao extends DAO<SalaryAdoptItem> {

	/**
	 * 查询指定申请件编号的可采纳项记录
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	SalaryAdoptItem selectByAppId(String appId);

	/**
	 * 删除指定申请件编号的可采纳项记录
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	int deleteByAppId(String appId);

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
