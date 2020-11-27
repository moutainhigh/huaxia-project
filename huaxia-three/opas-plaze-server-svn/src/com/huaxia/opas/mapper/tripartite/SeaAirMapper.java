package com.huaxia.opas.mapper.tripartite;

import java.util.Map;

import com.huaxia.opas.domain.tripartite.SeaAir;

public interface SeaAirMapper {
	/**
	 *@Title:insertSeaAirData
	 *@Description:插入海航表数据
	 *@param seaAir
	 *@author: gaohui
	 *@Date:2018年1月10日上午9:14:46
	 */
	void insertSeaAirData(SeaAir seaAir);
	/**
	 *@Title:selectCountByAppId
	 *@Description:根据申请件获取海航条数
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月10日下午1:39:21
	 */
	int selectCountByAppId(String appId);
	
	void updateCardC1SeaMemberId(Map<String, Object> params);
	void updateCardC2SeaMemberId(Map<String, Object> params);
}
