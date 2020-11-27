package com.huaxia.opas.dao.thirdparty;

import java.util.Map;

/**
 * 第三方-学历接口
 * 
 * @author zhiguo.li
 *
 */
public interface EducationDao {

	/**
	 * 查询摘要信息
	 * 
	 * @param appId
	 *            申请件编号
	 */
	Map<String, String> selectSummaryInfo(String appId);
	/**
	 * 查询摘要信息-查询结果
	 * 
	 * @param appId
	 *            申请件编号
	 */
	String querySummaryInfoQuery(String appId);
	/**
	 * 详细信息
	 * 
	 * @param appId
	 *            申请件编号
	 */
	Map<String, String> selectDetailInfo(String appId);
	
}
