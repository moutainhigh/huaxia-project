package com.huaxia.opas.service;

import java.util.Map;

import com.huaxia.opas.domain.PBOC;

/**
 * 人行服务接口
 * 
 * @author zhiguo.li
 *
 */
public interface PBOCService {

	/**
	 * 保存人行信息
	 * 
	 * @param pboc
	 *            人行对象
	 * @return
	 */
	int save(PBOC pboc);

	/**
	 * @Title:queryCountPbocByAppId
	 * @Description:根据appId查询 人行数据有几条
	 * @param appId
	 * @return
	 * @author: gaohui
	 * @Date:2017年8月30日下午2:19:15
	 */
	int queryCountPbocByAppId(String appId);
	/**
	 *@Title:queryLateAppIdByDayNameIdNo
	 *@Description:根据证件号和姓名和天数限制 获取最近查过人行的申请件
	 *@param params
	 *@return
	 *@author: gaohui
	 *@Date:2018年1月26日下午4:58:17
	 */
    String queryLateAppIdByDayNameIdNo(Map<String,String> params);
    /**
     *@Title:saveClonePbocData
     *@Description:保存通过lastAppId(最近30天查过人行的app_id)克隆到新的app_id中的人行数据
     *@param params
     *@return
     *@author: gaohui
     *@Date:2018年1月29日上午11:02:16
     */
    Map<String,Object> saveClonePbocData(Map<String,Object> params);
    /**
     *@Title:savePbocUpdateDataAdapterAction
     *@Description:人行 解析入库业务逻辑
     *@param pboc
     *@param appId
     *@param taskType
     *@author: gaohui
     *@Date:2018年3月29日下午4:25:18
     */
    void savePbocUpdateDataAdapterAction(PBOC pboc,String appId,String taskType);
}
