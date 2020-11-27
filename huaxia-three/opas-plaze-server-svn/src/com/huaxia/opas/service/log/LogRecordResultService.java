package com.huaxia.opas.service.log;

import com.huaxia.opas.domain.log.LogRecordResult;

/**
 * 日志记录结果服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface LogRecordResultService {

	/**
	 * 保存记录结果日志信息
	 * 
	 * @param logRecordResult
	 *            记录结果日志对象
	 * @return
	 */
	int save(LogRecordResult logRecordResult);

	/**
	 * 保存记录结果日志信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @param moduleName
	 *            第三方模块名称
	 * @param flagDispose
	 *            处理标志
	 * @param dispose
	 *            失败记录失败代码和描述（即"disposeCode"和"disposeDesc"）
	 * @return
	 */
	int save(String appId, String moduleName, boolean flagDispose, String... dispose);

}
