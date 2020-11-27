package com.huaxia.opas.dao.collect;

import com.huaxia.opas.dao.DAO;
import com.huaxia.opas.domain.collect.SalaryCompute;

/**
 * 增强信息收入计算采集接口
 * 
 * @author zhiguo.li
 *
 */
public interface SalaryComputeDao extends DAO<SalaryCompute> {

	/**
	 * 查询指定申请件编号的记录
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	SalaryCompute selectByAppId(String appId);

}
