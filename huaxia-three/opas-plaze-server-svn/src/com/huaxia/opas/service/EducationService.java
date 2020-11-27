package com.huaxia.opas.service;

import com.huaxia.opas.domain.Education;

/**
 * 学历服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface EducationService {

	/**
	 * 保存学历信息
	 * 
	 * @param education
	 *            学历对象
	 * @return
	 */
	int save(Education education);

	/**
	 * 判断指定编号申请件是否存在
	 * 
	 * @param appId
	 *            申请件编号
	 * @return
	 */
	int getCountByAppId(String appId);
	/**
	 *@Title:saveEducationUpdateDataAdapterAction
	 *@Description:学历入库
	 *@param education
	 *@param appId
	 *@param taskType
	 *@author: gaohui
	 *@Date:2018年3月29日下午4:40:03
	 */
	void saveEducationUpdateDataAdapterAction(Education education,String appId,String taskType);
}
