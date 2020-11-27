package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

/**
 * 第三方-公安接口
 * 
 * @author zhiguo.li
 *
 */
public interface PoliceService {

	/**
	 * 查询摘要信息
	 * 
	 * @param params
	 *            查询参数集
	 */
	Map<String, String> selectSummaryInfo(Map<String, String> params);

	/**
	 * 查询详细信息
	 * @param appId
	 * @return
	 */

	Map<String, String> selectDetailInfo(Map<String, String> params);

	List<Object> selectDetailList(Map<String, String> params);
	
	Map<String, String> querytaskStatusInfoByAppId(String appId);
/**
 * 公安查询详细信息
 * @param map
 * @return
 */
	Map<String, String> selectPoliceDetailInfo(Map<String, String> map);
	/**
	 *@Title:queryPoliceCountByAppId
	 *@Description:根据appId获取 简项公安的 条数（判断此流水号是否存在时用）
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年11月9日上午9:18:43
	 */
	int queryPoliceCountByAppId(String appId);

	/**
	 * 人像比对详细信息
	 * @param map
	 * @return
	 */
	Map<String, String> selectPoliceXpInfo(String appId);

	Map<String, String> selectPoliceXpDetail(String appId);

	Map<String, String> selectpoliceSummaryInfo(Map<String, String> param);

	Map<String, String> querySummaryByAppId(Map<String, Object> map);

	Map<String, String> selectAuthResultByAppid(String appId);


}
