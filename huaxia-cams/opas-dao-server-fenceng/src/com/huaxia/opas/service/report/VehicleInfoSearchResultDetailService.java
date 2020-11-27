package com.huaxia.opas.service.report;

import java.util.Map;

/**
 * 
 * @author dingguofeng
 * 车辆信息查询结果明细报表
 *
 */

public interface VehicleInfoSearchResultDetailService {
	
	/**
	 * @Title:findVehicleInfoSearchResultDetailDataByDate
	 * @Description:根据时间区间,从相应的表中获取所需的数据
	 * @author dingguofeng
	 * @param paraMap , pageNum, pageRows
	 * @return
	 * @Date:2017-10-27
	 */
	public Map<String, Object> findVehicleInfoSearchResultDetailDataByDate(Map<String, Object> paraMap, int pageNum, int pageRows);

}
