package com.huaxia.opas.service.tripartite;

import java.util.Map;

import com.huaxia.opas.domain.tripartite.QyhyInfo;

/**
 * 企业行业信息
 * 
 * @author gaoh
 *
 */
public interface QyhyInfoService {

	/**
	 *@Title:getCountByAppId
	 *@Description:根据申请件获取企业行业数据条数
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年4月20日下午1:32:51
	 */
	int getCountByAppId(String appId);
	/**
	 *@Title:saveQyhyInfoUpdateDataAdapterAction
	 *@Description:保存企业行业信息
	 *@param qyhyInfo
	 *@param appId
	 *@param qyhyTaskType
	 *@author: gaohui
	 *@Date:2018年4月23日下午3:40:22
	 */
	Map<String, String> saveQyhyInfoUpdateDataAdapterAction(QyhyInfo qyhyInfo,String appId,String qyhyTaskType)throws Exception;

}