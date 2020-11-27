package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.report.WsPlatformDataStatistics;

/**
 * 进件情况统计表
 * @author wenyh
 */
public interface WsPlatformDataStatisticsDao {
	/**
	 *@Title:selectWsPlatformDataStatistics
	 *@Description:根据查询条件从进件情况统计表等表中获取所需数据
	 *@param paramsMap
	 *@return
	 *@author: wenyh
	 *@Date:2020年7月2日
	 */
	public List<WsPlatformDataStatistics> selectWsPlatformDataStatistics(Map<String, Object> paramsMap);
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