package com.huaxia.opas.dao.tripartite;

import java.util.List;

import com.huaxia.opas.domain.tripartite.TYAntifraudData;
import com.huaxia.opas.domain.tripartite.TYAntifraudRiskInfo;

public interface TYAntifraudDao {
   
	/**
	 *@Title:selectCountByAppId
	 *@Description:根据申请件获取天御分条数
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2018年2月8日下午13:38:08
	 */
	int selectCountByAppId(String appId);
	/**
	 *@Title:insertTyData
	 *@Description:插入天御分主表数据
	 *@param tyAntifraudData
	 *@author: gaohui
	 *@Date:2018年2月9日上午9:06:00
	 */
	void insertTyData(TYAntifraudData tyAntifraudData);
	/**
	 *@Title:insertBatchTyRisk
	 *@Description:批量插入天御分风险代码数据
	 *@param listTYAntifraudRiskInfo
	 *@author: gaohui
	 *@Date:2018年2月9日上午9:10:43
	 */
	void insertBatchTyRisk(List<TYAntifraudRiskInfo> listTYAntifraudRiskInfo);
}