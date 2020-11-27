package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

/**
 * 第三方-通讯运营商
 * 
 * @author gaoh
 *
 */
public interface TxOperatorService {

	/**
	 *@Title:queryTxOperSummaryInfoByAppId
	 *@Description:根据appId查询通讯运营商摘要信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年2月27日下午3:45:03
	 */
	Map<String,String> queryTxOperSummaryInfoByAppId(String appId);

	Map<String, String> queryUnicomAddressInfoByAppId(String appId);

	List<Map<String, String>> queryUnicomListMessage(String appId);

	Map<String, String> querySfrzBaseData(String appId);

	Map<String, String> queryNbBaseInfo(String appId);
}
