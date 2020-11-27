package com.huaxia.opas.service.report;

import java.util.Map;

/**
 * 
 * @author dingguofeng
 * 车辆信息查询结果汇总报表
 *
 */

public interface VehicleInfoSearchResultService {
	
	/**
	 * @Title:findVehicleInfoSearchResultDataByDate
	 * @Description:根据时间区间,从相应的表中获取所需的数据
	 * @author dingguofeng
	 * @param paraMap
	 * @return
	 * @Date:2017-10-25
	 */
	public Map<String, Object> findVehicleInfoSearchResultDataByDate(Map<String, Object> paraMap);

}
