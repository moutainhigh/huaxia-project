/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;

/**
 * @author gaohui (不同粒度进件情况统计表)
 *
 */
public interface DifferentGrainStatisticsService {
	
	/**
	*@Title:DifferentGrainStatistics
	*@Description:获取维度类型、进件数量、核准、拒绝、未完成、审核量、核准率、拒绝率、待处理率字段数据，通过渠道（条形码规则）、地区（条形码规则）、申请卡产品、录入商（条形码规则）、流水号字母（条形码字母）（条形码字母）
	*@param paraMap 前台传来的查询条件
	*@return
	*@author: gaohui
	*@Date:2017年3月20日上午9:31:44
	 */
	public Map<String, Object> findDataDifferentGrainStatisticsByDateDimension(Map<String, Object> paraMap);

	public List<Map<String, Object>> queryApplicationCardProducts();
}
