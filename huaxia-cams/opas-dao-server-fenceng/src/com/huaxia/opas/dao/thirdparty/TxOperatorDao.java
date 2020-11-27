package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

/**
 *第三方-通讯运营商
 * 
 * @author gaoh
 *
 */
public interface TxOperatorDao {
	/**
	 *@Title:selectTxOperSummaryInfoByAppId
	 *@Description:根据appId获取通讯运营商信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年7月13日上午10:02:53
	 */
	Map<String,String> selectTxOperSummaryInfoByAppId(String appId);

	Map<String, String> selectUnicomAddressInfoByAppId(String appId);

	List<Map<String, String>> selectUnicomListMessage(String appId);

	String querySfrzBaseData(Map<String, String> map);

	Map<String, String> queryNbBaseInfo(String appId);

}
