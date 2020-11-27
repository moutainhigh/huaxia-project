package com.huaxia.opas.service.thirdparty;

import java.util.Map;

/**
 * 第三方-腾讯天御分接口
 * 
 * @author gaoh
 *
 */
public interface TianYuService {

	/**
	 *@Title:queryTianYuSummaryInfoByAppId
	 *@Description:根据appId查询腾讯天御分摘要信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年2月27日下午3:45:03
	 */
	Map<String,String> queryTianYuSummaryInfoByAppId(String appId);
}
