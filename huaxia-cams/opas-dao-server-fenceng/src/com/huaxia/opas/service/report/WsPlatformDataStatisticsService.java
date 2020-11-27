/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.List;
import java.util.Map;

/**
 * @author wenyh (进件情况统计表)
 *
 */
public interface WsPlatformDataStatisticsService {
	/**
	 *@Title:findWsPlatformDataStatistics
	 *@Description:根据查询条件从进件情况统计表等表中获取所需数据
	 *@param paraMap
	 *@return
	 *@author: wenyh
	 *@Date:2020年7月2日
	 */
	public Map<String, Object> findWsPlatformDataStatistics(Map<String, Object> paraMap);
	/**
	 * 获取公司编码
	 * @return
	 */
	public List<Map<String, Object>> queryBusinessNumber();
	/**
	 * 获取模块编码
	 * @param orgName
	 * @return
	 */
	public List<Map<String, Object>> queryModuleNumber(String businessNumber);
}
