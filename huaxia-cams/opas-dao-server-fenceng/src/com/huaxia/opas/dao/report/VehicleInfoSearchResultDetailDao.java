package com.huaxia.opas.dao.report;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author dingguofeng
 *车辆信息查询结果明细报表
 *
 */

public interface VehicleInfoSearchResultDetailDao {
	
	/**
	 * @Title:selectVehicleInfoSearchResultDetailDataByDate
	 * @Description:根据时间区间,从相应的表中获取所需的数据
	 * @author dingguofeng
	 * @param paramsMap
	 * @return
	 * @Date:2017-10-27
	 */
	public List<Map<String, Object>> selectVehicleInfoSearchResultDetailDataByDate(Map<String, Object> paramsMap,int pageNum, int pageRows);
	
	/**
	*@Title:getCountVehicleInfoSearchResultDetailDataByDate
	*@Description:获取符合条件（日期时间）的总条数
	*@param paraMap
	*@return
	*@author: dingguofeng
	*@Date:2017年10月30日
	 */
	public String getCountVehicleInfoSearchResultDetailDataByDate(Map<String, Object> paraMap);
	
	/**
	*@Title:getC2CnameFromTableC2
	*@Description:根据appid从C2表获取单独申请附卡的申请人姓名
	*@param paraMap
	*@return
	*@author: dingguofeng
	*@Date:2017年11月17日
	 */
	public String getC2CnameFromTableC2(Map<String, Object> appid);
}
