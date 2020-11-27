package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.report.EntryStatistics;

/**
 * @author gaohui(进件情况统计表)
 *
 */
public interface BuyPieceSituationStatisticsDao {
	/**
	*@Title:selectBuyPieceSituationStatisticsDataByDate
	*@Description:根据时间区间获取 进件情况统计表等表的相关信息
	*@param paramsMap
	*@return
	*@author: gaohui
	*@Date:2017年3月20日下午4:24:10
	 */
	public List<EntryStatistics> selectBuyPieceSituationStatisticsDataByDate(Map<String, Object> paramsMap);

}