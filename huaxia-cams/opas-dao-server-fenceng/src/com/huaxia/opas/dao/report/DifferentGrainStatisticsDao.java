package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

/**
 * @author gaohui(不同粒度进件情况统计表)
 *
 */
public interface DifferentGrainStatisticsDao {
	/**
	*@Title:selectDifferentGrainStatisticsDataByDateDimension
	*@Description:从 不同粒度进件情况统计等表中， 按进件日期区间对进件情况进行多维度统计
	*@param paramsMap
	*@return
	*@author: gaohui
	*@Date:2017年3月19日下午8:03:07
	 */
	public List<Map<String, Object>> selectDifferentGrainStatisticsDataByDateDimension(Map<String, Object> paramsMap);

	public List<Map<String, Object>> selectApplicationCardProducts();

}