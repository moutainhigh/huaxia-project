package com.huaxia.opas.dao;

import java.util.Map;

public interface PBOCPersonalInfoDao {

	int insert(Map<String, Object> entity);
	
	int selectCountPbocByAppId(String appId);
	/**
	 *@Title:selectLateAppIdByDayNameIdNo
	 *@Description:根据证件号和姓名和天数限制 获取最近查过人行的申请件
	 *@param params
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月26日下午5:01:28
	 */
	String selectLateAppIdByDayNameIdNo(Map<String,String> params);
	/**
     *@Title:saveClonePbocData
     *@Description:保存通过lastAppId(最近30天查过人行的app_id)克隆到新的app_id中的人行数据
     *@param params
     *@return
     *@author: gaohui
     *@Date:2018年1月29日上午11:02:16
     */
    Map<String,Object> saveClonePbocData(Map<String,Object> params);
}
