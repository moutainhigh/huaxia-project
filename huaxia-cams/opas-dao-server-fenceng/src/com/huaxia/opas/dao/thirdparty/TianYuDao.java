package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

/**
 * 第三方-腾讯天御分接口
 * 
 * @author gaoh
 *
 */
public interface TianYuDao {
	/**
	 *@Title:selectTianYuScoreInfoByAppId
	 *@Description:根据appId获取天御分信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年2月27日下午3:52:28
	 */
	Map<String,String> selectTianYuScoreInfoByAppId(String appId);
	/**
	 *@Title:selectTianYuRiskCodeByAppId
	 *@Description:根据appId获取天御分风险码信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年2月27日下午3:53:45
	 */
	List<Map<String,String>> selectTianYuRiskCodeByAppId(String appId);
}
