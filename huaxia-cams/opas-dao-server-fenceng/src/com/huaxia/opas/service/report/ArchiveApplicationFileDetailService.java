/**
 * 
 */
package com.huaxia.opas.service.report;

import java.util.Map;

/**
 * @author gaohui (归档申请件明细表)
 *
 */
public interface ArchiveApplicationFileDetailService {
	/**
	*@findArchiveApplicationFileDetailDataByDateDimension
	*@Description:获取维度类型、归档数量、核准、拒绝、核准率、拒绝率等字段数据，通过渠道（条形码规则）、地区（条形码规则）、申请卡产品、录入商（条形码规则）、流水号字母（条形码字母）（条形码字母）
	*@param paraMap 前台传来的查询条件
	*@return
	*@author: gaohui
	*@Date:2017年3月16日上午10:11:33
	 */
	public Map<String, Object> findArchiveApplicationFileDetailDataByDateDimension(Map<String, Object> paraMap);
}
