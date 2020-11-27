package com.huaxia.opas.service.tripartite;

import java.util.Map;

/**
 * 第三方-海航与信审的交互
 * 
 * @author gaohui
 *
 */
public interface SeaAirSfService {
	
	/**
	 *@Title:updateCardC1C2SeaMemberId
	 *@Description:修改主卡进件表 c1、c2 的海航会员号
	 *@param params
	 *@author: gaohui
	 *@Date:2018年1月18日下午6:37:55
	 */
	void updateCardC1C2SeaMemberId(Map<String,Object> params);
	
}
