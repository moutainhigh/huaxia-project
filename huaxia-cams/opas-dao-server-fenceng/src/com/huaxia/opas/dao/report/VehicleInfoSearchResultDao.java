package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author dingguofeng
 * 车辆信息查询结果汇总报表
 *
 */

public interface VehicleInfoSearchResultDao {
	
	/**
	 * @Title:selectVehicleInfoSearchResultDataByDate
	 * @Description:根据时间区间,从相应的表中获取所需的数据
	 * @author dingguofeng
	 * @param paramsMap
	 * @return
	 * @Date:2017-10-25
	 */
	public List<Map<String, Object>> selectVehicleInfoSearchResultDataByDate(Map<String, Object> paramsMap);

}
