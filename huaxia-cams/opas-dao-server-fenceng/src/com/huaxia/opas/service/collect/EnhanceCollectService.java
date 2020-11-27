package com.huaxia.opas.service.collect;

import java.util.Date;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.collect.EnhanceBlazeResult;
import com.huaxia.opas.domain.collect.EnhanceInfo;
import com.huaxia.opas.service.BaseService;

/**
 * 增强信息采集服务
 * 
 * @author zhiguo.li
 *
 */
public interface EnhanceCollectService extends BaseService<EnhanceInfo> {

	/**
	 * 保存对象
	 * 
	 * @param objects
	 *            参数对象
	 * @return
	 * @throws CoreException
	 */
	int saveAll(Map<String, Object> objects) throws CoreException;

	/**
	 * 合并对象
	 * 
	 * @param objects
	 *            参数对象
	 * @return
	 * @throws CoreException
	 */
	int mergeAll(Map<String, Object> objects) throws CoreException;

	/**
	 * 根据申请件编号查询增强信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	EnhanceInfo getByAppId(String appId);
	/**
	 * 根据申请件编号查询入职时间
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	 Date selectEntryYearByAppId(String appId);

	/**
	 * 更新增强采集BLAZE响应结果
	 * 
	 * @param blaze
	 *            BLAZE结果对象
	 * @return
	 */
	int updateBlazeResult(EnhanceBlazeResult blaze);
	/**
	 * 工作年限
	 * 
	 * @param appId
	 *            
	 * @return
	 */
	int selectC1CoyrByAppId(String appId);

	/**
	 * 根据申请件编号查询增强信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	String  getRequestTypeByAppId(String appId);
}
