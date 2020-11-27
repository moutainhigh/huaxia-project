/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.Map;

/**
 * @author gaohui (进件情况统计表)
 *
 */
public interface BuyPieceSituationStatisticsService {
	/**
	*@Title:findBuyPieceSituationStatisticsDataByDate
	*@Description:根据时间区间 从进件情况统计表等表中获取所需数据
	*@param paraMap
	*@return
	*@author: gaohui
	*@Date:2017年3月20日下午4:19:59
	 */
	public Map<String, Object> findBuyPieceSituationStatisticsDataByDate(Map<String, Object> paraMap);
}
