package com.huaxia.opas.service.tripartite;

import com.huaxia.opas.domain.tripartite.TYAntifraud;

/**
 * 腾讯天御分
 * 
 * @author gaohui 
 *
 */
public interface TYAntifraudService {

	/**
	 *@Title:getCountByAppId
	 *@Description:根据申请件获取腾讯天御分表数据条数
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月10日下午1:32:51
	 */
	int getCountByAppId(String appId);
	/**
	 *@Title:save
	 *@Description:保存腾讯天御分数据
	 *@param tyAntifraud
	 *@author: gaohui
	 *@Date:2018年2月8日下午5:21:11
	 */
	void save(TYAntifraud tyAntifraud);
	/**
	 *@Title:saveTYAntiUpdateDataAdapterAction
	 *@Description:腾讯天御分 解析入库
	 *@param tyAntifraud
	 *@param appId
	 *@param tyTaskType
	 *@author: gaohui
	 *@Date:2018年3月29日下午4:20:12
	 */
	void saveTYAntiUpdateDataAdapterAction(TYAntifraud tyAntifraud,String appId,String tyTaskType);
}