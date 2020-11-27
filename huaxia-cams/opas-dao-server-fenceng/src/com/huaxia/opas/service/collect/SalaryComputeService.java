package com.huaxia.opas.service.collect;

import com.huaxia.opas.domain.collect.SalaryCompute;
import com.huaxia.opas.service.BaseService;

/**
 * 增强信息收入计算采集服务
 * 
 * @author zhiguo.li
 *
 */
public interface SalaryComputeService extends BaseService<SalaryCompute> {

	/**
	 * 查询指定申请件编号的收入计算对象
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	SalaryCompute getByAppId(String appId);

}
