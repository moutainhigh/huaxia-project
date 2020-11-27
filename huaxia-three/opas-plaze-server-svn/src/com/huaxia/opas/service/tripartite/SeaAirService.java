package com.huaxia.opas.service.tripartite;


import java.util.Map;

import com.huaxia.opas.domain.tripartite.SeaAir;

/**
 * 海航 
 * 
 * @author gaohui 
 *
 */
public interface SeaAirService {
	/**
	 *@Title:save
	 *@Description:保存海航实体类
	 *@param seaAir
	 *@author: gaohui
	 *@Date:2018年1月9日下午5:03:04
	 */
	void save(SeaAir seaAir);
	/**
	 *@Title:getCountByAppId
	 *@Description:根据申请件获取海航表数据条数
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月10日下午1:32:51
	 */
	int getCountByAppId(String appId);
	
	/**
	 *@Title:updateCardC1C2SeaMemberId
	 *@Description:修改主卡进件表 c1、c2 的海航会员号
	 *@param params
	 *@author: dingguofeng
	 */
	void updateCardC1C2SeaMemberId(Map<String,Object> params);
	
}
